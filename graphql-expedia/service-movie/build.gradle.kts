plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common:service-movie"))
    implementation("com.expediagroup:graphql-kotlin-spring-server")
}
