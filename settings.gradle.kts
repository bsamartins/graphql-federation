rootProject.name = "graphql-federation"

pluginManagement {
    val quarkusVersion: String by settings
    val kotlinVersion: String by settings
    val springBootVersion: String by settings

    repositories {
        gradlePluginPortal()
    }

    plugins {
        id("io.quarkus") version quarkusVersion
        id("io.quarkus.extension") version quarkusVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.springframework.boot") version springBootVersion
    }
}

include(":common:service-movie")
include(":common:service-director")

include(":graphql-dgs:service-movie")
include(":graphql-dgs:service-director")

include(":graphql-spqr:service-movie")
include(":graphql-spqr:service-director")
include(":graphql-spqr:graphql-spqr-federation")

include(":graphql-quarkus:federation:deployment")
include(":graphql-quarkus:federation:runtime")
include(":graphql-quarkus:service-movie")
include(":graphql-quarkus:service-director")
