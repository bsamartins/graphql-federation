package io.bsamartins.service.movie

data class MovieModel(val movieId: Int, val title: String, val director: DirectorModel)
data class DirectorModel(val directorId: Int)