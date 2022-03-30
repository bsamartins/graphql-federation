package io.bsamartins.service.director

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DirectorApplication {

    @Bean
    fun entitiesDataFetcher() = EntitiesDataFetcher()
}

fun main() {
    runApplication<DirectorApplication>()
}
