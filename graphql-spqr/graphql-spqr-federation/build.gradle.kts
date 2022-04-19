plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("io.leangen.graphql:graphql-spqr-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.apollographql.federation:federation-graphql-java-support")
}
