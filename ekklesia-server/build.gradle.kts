import kotlin.math.log

plugins {
    kotlin("jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"

    application
}

group = "com.kcanoe.ekklesia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    val logbackVersion = "1.5.13"

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    val ktorVersion = "3.2.3"

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}

application {
    mainClass = "com.kcanoe.ekklesia.MainKt"
}
