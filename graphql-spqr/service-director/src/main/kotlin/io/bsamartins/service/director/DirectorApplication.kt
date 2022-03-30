package io.bsamartins.service.director

import io.bsamartins.director.DirectorService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DirectorApplication {

    @Bean
    fun entitiesDataFetcher(
        directorService: DirectorService,
    ): EntitiesDataFetcher = EntitiesDataFetcher(directorService)

    @Bean fun directorService() = DirectorService()
}

fun main() {
    runApplication<DirectorApplication>()
}
