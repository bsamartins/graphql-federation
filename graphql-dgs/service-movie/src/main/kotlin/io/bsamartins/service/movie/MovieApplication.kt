package io.bsamartins.service.movie

import io.bsamartins.movie.MovieService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MovieApplication {
    @Bean
    fun movieService() = MovieService()
}

fun main() {
    runApplication<MovieApplication>()
}