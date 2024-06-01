package com.jgpl.kmpmovies.screen.home

import com.jgpl.kmpmovies.domain.model.Movie
import com.jgpl.kmpmovies.screen.home.vo.MovieItemVo

class HomeMapper {

    fun mapToMovieItemVoList(models: List<Movie>) = models.map { mapToMovieItemVo(it) }

    private fun mapToMovieItemVo(model: Movie) = MovieItemVo(
        title = model.title
    )

}