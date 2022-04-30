package io.bsamartins.director

class DirectorService {

    private val directors = listOf(
        Director(id = 1, "Stanly Kubrick")
    ).associateBy { it.id }

    fun findAll(): List<Director> = directors.values.toList()

    fun findById(directorId: Int): Director? = directors[directorId]
}

data class Director(val id: Int?, val name: String)
