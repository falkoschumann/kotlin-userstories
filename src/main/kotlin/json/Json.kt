package de.muspellheim.userstories.json

sealed class JsonValue
class JsonString : JsonValue()
class JsonNumber : JsonValue()
class JsonObject : JsonValue()
class JsonArray : JsonValue()
object JsonTrue : JsonValue()
object JsonFalse : JsonValue()
object JsonNull : JsonValue()
