plugins {
    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
}

group = "io.bsamartins.graphql.federation"
version = "1.0-SNAPSHOT"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        val springBootVersion: String by project
        val spqrVersion: String by project
        val dgsVersion: String by project

        val implementation by configurations
        implementation(kotlin("stdlib"))

        constraints {
            add("implementation", "org.springframework.boot:spring-boot-dependencies:$springBootVersion")
            add("implementation", "io.leangen.graphql:graphql-spqr-spring-boot-starter:$spqrVersion")
            add("implementation", "com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:$dgsVersion")
        }
    }
}


