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

        constraints {
            add("implementation", "org.springframework.boot:spring-boot-dependencies:2.6.3")
            add("implementation", "io.leangen.graphql:graphql-spqr-spring-boot-starter:0.0.6")
            add("implementation", "com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release")
        }
    }
}


