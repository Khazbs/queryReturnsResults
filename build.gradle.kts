plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "ru.shindei"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("ru.shindei.qrr.MainKt")
}

dependencies {
    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.dao)
    implementation(libs.sqlite.jdbc)
    implementation(libs.slf4j.simple)
}
