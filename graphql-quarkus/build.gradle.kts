subprojects {
    dependencies {
        val quarkusVersion: String by project
        implementation(platform("io.quarkus:quarkus-bom:$quarkusVersion"))
        constraints {
            implementation("io.smallrye:smallrye-graphql-federation-runtime:1.4.4")
        }
    }
}
