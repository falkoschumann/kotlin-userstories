/*
 * User Stories
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.userstories.json

// TODO Fabrikmethoden für read und write

sealed class JsonValue

data class JsonString(val value: String) : JsonValue() {
    override fun toString(): String {
        return value
    }
}

data class JsonNumber(val value: Number) : JsonValue() {
    override fun toString(): String {
        return value.toString()
    }
}

class JsonObject(initialMembers: Map<String, JsonValue> = emptyMap()) : JsonValue(), Iterable<JsonObject.Member> {

    private val members = mutableMapOf<String, JsonValue>()

    init {
        members.putAll(initialMembers)
    }

    operator fun get(name: String): JsonValue = members[name] ?: error("member does not exist: $name")

    operator fun set(name: String, value: String): JsonObject {
        return set(name, jsonOf(value))
    }

    operator fun set(name: String, value: Number): JsonObject {
        return set(name, jsonOf(value))
    }

    operator fun set(name: String, value: Boolean): JsonObject {
        return set(name, jsonOf(value))
    }

    operator fun set(name: String, value: JsonValue): JsonObject {
        members[name] = value
        return this
    }

    fun size(): Int = members.size
    fun isEmpty(): Boolean = members.isEmpty()
    fun isNotEmpty(): Boolean = members.isNotEmpty()
    fun names(): List<String> = members.keys.toList()

    override fun iterator(): Iterator<Member> {
        val names = names()
        var index = 0
        return object : Iterator<Member> {
            override operator fun next(): Member {
                val name = names[index++]
                val value = members[name]
                return Member(name, value!!)
            }

            override operator fun hasNext(): Boolean {
                return names.size < index
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JsonObject

        if (members != other.members) return false

        return true
    }

    override fun hashCode(): Int {
        return members.hashCode()
    }

    override fun toString(): String {
        return members.toString()
    }

    data class Member(val name: String, val value: JsonValue)
}

class JsonArray(initialValues: List<JsonValue> = emptyList()) : JsonValue(), Iterable<JsonValue> {

    private val values = mutableListOf<JsonValue>()

    init {
        values.addAll(initialValues)
    }

    fun add(value: String): JsonArray {
        return add(jsonOf(value))
    }

    fun add(value: Number): JsonArray {
        return add(jsonOf(value))
    }

    fun add(value: Boolean): JsonArray {
        return add(jsonOf(value))
    }

    fun add(value: JsonValue): JsonArray {
        values.add(value)
        return this
    }

    operator fun get(index: Int): JsonValue = values[index]

    operator fun set(index: Int, value: String) {
        set(index, jsonOf(value))
    }

    operator fun set(index: Int, value: Number) {
        set(index, jsonOf(value))
    }

    operator fun set(index: Int, value: Boolean) {
        set(index, jsonOf(value))
    }

    operator fun set(index: Int, value: JsonValue) {
        values[index] = value
    }

    fun size(): Int = values.size
    fun isEmpty(): Boolean = values.isEmpty()
    fun isNotEmpty(): Boolean = values.isNotEmpty()
    fun values(): List<JsonValue> = values.toList()

    override fun iterator(): Iterator<JsonValue> = values.iterator()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as JsonArray
        if (values != other.values) return false
        return true
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        return values.toString()
    }
}

object JsonTrue : JsonValue() {
    override fun toString(): String {
        return "true"
    }
}

object JsonFalse : JsonValue() {
    override fun toString(): String {
        return "false"
    }
}

object JsonNull : JsonValue() {
    override fun toString(): String {
        return "null"
    }
}

fun jsonOf(value: String): JsonValue {
    return JsonString(value)
}

fun jsonOf(value: Number): JsonValue {
    return JsonNumber(value)
}

fun jsonOf(value: Map<String, JsonValue>): JsonObject {
    return JsonObject(value)
}

fun jsonOf(values: List<JsonValue>): JsonArray {
    return JsonArray(values)
}

fun jsonOf(vararg values: JsonValue): JsonArray {
    return jsonOf(values.toList())
}

fun jsonOf(value: Boolean): JsonValue {
    return if (value) JsonTrue else JsonFalse
}

fun jsonNull(): JsonValue {
    return JsonNull
}
