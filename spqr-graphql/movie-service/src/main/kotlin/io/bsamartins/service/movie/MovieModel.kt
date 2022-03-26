package io.bsamartins.service.movie

data class MovieModel(
    val id: Int,
    val title: String,
    val actors: List<Int>
)