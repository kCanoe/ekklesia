plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.kcanoe.ekklesia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    val ktorVersion = "3.2.3"

    implementation("io.ktor:ktor-server-core:${ktorVersion}")
    implementation("io.ktor:ktor-server-netty:${ktorVersion}")

    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}