package com.arthur.tmdbapp.data.mapper

import com.arthur.tmdbapp.data.network.model.MovieDetailsDto
import com.arthur.tmdbapp.data.network.service.MovieService
import com.arthur.tmdbapp.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailsDtoMapper @Inject constructor() {
    fun map(dto: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            id = dto.id,
            title = dto.title,
            posterUrl = dto.posterPath?.let { MovieService.IMAGE_BASE_URL + it },
            overview = dto.overview ?: "",
            releaseDate = dto.releaseDate ?: "",
            genres = dto.genres?.map { it.name } ?: emptyList(),
        )
    }

    fun mapList(dtoList: List<MovieDetailsDto>): List<MovieDetails> {
        return dtoList.map { map(it) }
    }
}