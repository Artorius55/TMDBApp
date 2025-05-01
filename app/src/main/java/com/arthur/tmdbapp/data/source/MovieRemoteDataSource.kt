package com.arthur.tmdbapp.data.source

import com.arthur.tmdbapp.data.network.model.MovieDetailsDto
import com.arthur.tmdbapp.data.network.response.PopularMoviesResponseDto
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(page: Int, language: String): Response<PopularMoviesResponseDto>
    suspend fun getMovieDetails(movieId: Int, language: String): Response<MovieDetailsDto>
}