/*
 * User Stories
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.userstories.json

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JsonTests {

    @Test
    fun `create JSON values`() {
        assertEquals(JsonTrue, jsonOf(true), "true")
        assertEquals(JsonFalse, jsonOf(false), "false")
        assertEquals(JsonNull, jsonNull(), "null")
        assertEquals(JsonString("Foobar"), jsonOf("Foobar"), "string")
        assertEquals(JsonNumber(42), jsonOf(42), "number (Int)")
        assertEquals(JsonNumber(47.11), jsonOf(47.11), "number (Double)")
    }

    @Test
    fun `create JSON object`() {
        assertEquals(
            JsonObject().set("a", "Foo").set("b", 42),
            jsonOf(mapOf(Pair("a", jsonOf("Foo")), Pair("b", jsonOf(42)))),
            "object"
        )
    }

    @Test
    fun `create JSON arrray`() {
        assertEquals(
            JsonArray().add("Foo").add(42),
            jsonOf(jsonOf("Foo"), jsonOf(42)),
            "array"
        )
    }
}
