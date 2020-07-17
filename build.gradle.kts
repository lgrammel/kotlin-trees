import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    base

    val kotlinVersion = "1.3.72"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

}

repositories {
    mavenCentral()
    jcenter()
}

apply(plugin = "kotlin")

dependencies {

    // Kotlin
    implementation(kotlin("stdlib"))

    // JUnit testing library
    val junitVersion = "5.3.1"
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
}

buildscript {

    repositories {
        jcenter()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.3.72"))
    }
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// configure Java 11 compatibility
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}
