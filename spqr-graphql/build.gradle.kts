plugins {
    id("org.springframework.boot") version "2.6.5" apply false
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        constraints {
            add("implementation", "io.leangen.graphql:graphql-spqr-spring-boot-starter:0.0.6")
        }
    }
}