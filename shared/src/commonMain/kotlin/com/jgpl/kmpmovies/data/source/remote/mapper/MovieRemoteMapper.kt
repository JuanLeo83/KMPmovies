package com.jgpl.kmpmovies.data.source.remote.mapper

import com.jgpl.kmpmovies.data.source.remote.dto.MovieDTO
import com.jgpl.kmpmovies.data.source.remote.dto.PopularMoviesResponseDTO
import com.jgpl.kmpmovies.domain.model.Movie

class MovieRemoteMapper {

    fun toMovieModelList(dto: PopularMoviesResponseDTO): List<Movie> =
        dto.results.map { toModel(it) }

    private fun toModel(dto: MovieDTO): Movie = Movie(
        adult = dto.adult,
        backdropPath = dto.backdropPath,
        genreIds = dto.genreIds,
        id = dto.id,
        originalLanguage = dto.originalLanguage,
        originalTitle = dto.originalTitle,
        overview = dto.overview,
        popularity = dto.popularity,
        posterPath = dto.posterPath,
        releaseDate = dto.releaseDate,
        title = dto.title,
        video = dto.video,
        voteAverage = dto.voteAverage,
        voteCount = dto.voteCount
    )
}