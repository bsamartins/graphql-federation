plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
}