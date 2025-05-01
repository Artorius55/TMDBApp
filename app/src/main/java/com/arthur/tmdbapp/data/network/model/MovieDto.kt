package com.arthur.tmdbapp.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val title: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("overview") val overview: String,
    @SerialName("release_date") val releaseDate: String,
)
