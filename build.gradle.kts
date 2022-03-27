plugins {
    kotlin("jvm") version "1.6.10" apply false
    kotlin("plugin.spring") version "1.6.10" apply false
    id("org.springframework.boot") version "2.6.5" apply false
}

group = "io.bsamartins.graphql.federation"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        val implementation by configurations
        implementation(kotlin("stdlib"))
    }
}


