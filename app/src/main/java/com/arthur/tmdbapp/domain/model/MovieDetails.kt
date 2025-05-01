package com.arthur.tmdbapp.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val releaseDate: String,
    val genres: List<String>
)
