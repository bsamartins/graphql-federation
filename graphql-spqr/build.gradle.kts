subprojects {
    dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies"))
        constraints {
            add("implementation", "com.apollographql.federation:federation-graphql-java-support:0.6.5")
            add("implementation", "com.graphql-java:graphql-java:16.2")
        }
    }
}