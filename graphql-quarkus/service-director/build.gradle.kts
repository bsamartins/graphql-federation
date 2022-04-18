plugins {
    id("io.quarkus")
}

dependencies {
    implementation("io.quarkus:quarkus-smallrye-graphql")
    implementation("io.smallrye:smallrye-graphql-federation-runtime")
    implementation(project(":graphql-quarkus:federation"))
    implementation(project(":graphql-quarkus:federation:deployment"))
    implementation(project(":common:service-director"))
}
