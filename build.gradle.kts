/*
 * User Stories
 * Copyright (c) 2020 Falko Schumann
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = "0.1.0"

plugins {
    application
    kotlin("jvm") version "1.3.61"
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

//
// Application
//

application {
    mainClassName = "de.muspellheim.userstories.AppKt"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClassName
        attributes["Implementation-Title"] = "User Stories"
        attributes["Implementation-Version"] = project.version
        attributes["Implementation-Vendor"] = "Falko Schumann"
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

//
// Kotlin
//

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

//
// JavaFX
//

javafx {
    version = "13"
    modules("javafx.controls", "javafx.fxml")
}
