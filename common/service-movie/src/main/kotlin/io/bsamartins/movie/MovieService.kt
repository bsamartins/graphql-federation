package io.bsamartins.movie

import java.math.BigDecimal

class MovieService {

    private val movies = listOf(
        Movie(id = 1, "Eyes Wide Shut", director = MovieDirector(id = 1, salary = 100_000.toBigDecimal())),
        Movie(id = 2, "Top Gun", director = MovieDirector(id = 2, salary = 500_000.toBigDecimal())),
    ).associateBy { it.id }

    fun findById(id: Int): Movie? {
        return movies[id]
    }

    fun findAll(): List<Movie> {
        return movies.values.toList()
    }
}

data class Movie(val id: Int, val title: String, val director: MovieDirector)
data class MovieDirector(val id: Int, val salary: BigDecimal)
