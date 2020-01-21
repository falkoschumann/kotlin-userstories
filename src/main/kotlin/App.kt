/*
 * User Stories
 * Copyright (c) 2020 Falko Schumann
 */

package de.muspellheim.userstories

class App {
    val greeting: String
        get() {
            return "Hello world."
        }
}

fun main(args: Array<String>) {
    println(App().greeting)
}
