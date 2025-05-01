package com.arthur.tmdbapp.data.mapper

import com.arthur.tmdbapp.data.network.model.MovieDto
import com.arthur.tmdbapp.data.network.service.MovieService
import com.arthur.tmdbapp.domain.model.Movie
import javax.inject.Inject

class MovieDtoMapper @Inject constructor() {
    fun map(dto: MovieDto): Movie {
        return Movie(
            id = dto.id,
            title = dto.title,
            posterUrl = dto.posterPath?.let { MovieService.IMAGE_BASE_URL + it },
            overview = dto.overview,
            releaseDate = dto.releaseDate
        )
    }

    fun mapList(dtoList: List<MovieDto>): List<Movie> {
        return dtoList.map { map(it) }
    }
}