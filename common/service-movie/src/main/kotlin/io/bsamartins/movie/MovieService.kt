package io.bsamartins.movie

class MovieService {

    private val movies = listOf(
        Movie(id = 1, "Eyes Wide Shut", directorId = 1),
        Movie(id = 2, "Top Gun", directorId = 2),
    ).associateBy { it.id }

    fun findById(id: Int): Movie? {
        return movies[id]
    }

    fun findAll(): List<Movie> {
        return movies.values.toList()
    }
}

data class Movie(val id: Int, val title: String, val directorId: Int)