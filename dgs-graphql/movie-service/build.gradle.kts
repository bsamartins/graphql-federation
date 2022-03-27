plugins {
    kotlin("plugin.spring")
    id("com.netflix.dgs.codegen")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
}

tasks.named<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask>("generateJava") {
    packageName = "io.bsamartins.service.movie.model"
}