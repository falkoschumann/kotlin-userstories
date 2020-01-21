package de.muspellheim.userstories.json

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

data class JsonObject(val members: Map<String, JsonValue>) : JsonValue() {
    operator fun get(key: String): JsonValue = members[key] ?: error("member does not exist: $key")
}

data class JsonArray(val values: List<JsonValue>) : JsonValue() {
    operator fun get(index: Int): JsonValue = values[index]
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
