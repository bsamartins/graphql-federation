package io.bsamartins.service.movie

import com.expediagroup.graphql.generator.federation.directives.ExtendsDirective
import com.expediagroup.graphql.generator.federation.directives.ExternalDirective
import com.expediagroup.graphql.generator.federation.directives.FieldSet
import com.expediagroup.graphql.generator.federation.directives.KeyDirective
import io.bsamartins.movie.Movie

@KeyDirective(fields = FieldSet("movieId"))
data class MovieModel(val movieId: Int, val title: String, val director: DirectorModel)

@ExtendsDirective
@KeyDirective(fields = FieldSet("directorId"))
data class DirectorModel(
    @ExternalDirective
    val directorId: Int,
    val salary: Double,
)

fun Movie.toModel(): MovieModel = MovieModel(
    movieId = this.id,
    title = this.title,
    director = DirectorModel(
        directorId = this.director.id,
        salary = this.director.salary.toDouble(),
    )
)
