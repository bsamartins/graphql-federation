plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common:service-director"))
    implementation("com.expediagroup:graphql-kotlin-spring-server")
}
