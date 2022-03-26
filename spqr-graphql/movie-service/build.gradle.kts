plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter")
}
