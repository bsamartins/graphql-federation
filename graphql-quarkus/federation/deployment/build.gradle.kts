plugins {
    `java-library`
}

dependencies {
    implementation("io.quarkus:quarkus-builder")
    implementation("io.quarkus:quarkus-core-deployment")
    implementation("io.quarkus:quarkus-vertx-http-deployment")
    implementation("io.quarkus:quarkus-smallrye-graphql-deployment")
    implementation("io.smallrye:smallrye-graphql-federation-runtime")
    implementation("com.apollographql.federation:federation-graphql-java-support")
    implementation(project(":graphql-quarkus:federation:runtime"))
}
