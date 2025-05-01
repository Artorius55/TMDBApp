package com.arthur.tmdbapp.data.network.service

import com.arthur.tmdbapp.data.network.model.MovieDetailsDto
import com.arthur.tmdbapp.data.network.response.PopularMoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<PopularMoviesResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = LANGUAGE
    ): Response<MovieDetailsDto>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val LANGUAGE = "es-MX"
    }
}