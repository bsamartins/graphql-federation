plugins {
    kotlin("jvm") version "1.6.10" apply false
}

group = "io.bsamartins.graphql.federation"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }
}


