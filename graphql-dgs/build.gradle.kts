plugins {
    id("com.netflix.dgs.codegen") version "5.1.17" apply false
}

subprojects {
    dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies"))
        implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies"))
    }
}
