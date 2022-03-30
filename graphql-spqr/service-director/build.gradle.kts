plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":common:service-director"))
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":graphql-spqr:graphql-spqr-federation"))
}