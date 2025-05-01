package com.arthur.tmdbapp.data.source.impl

import com.arthur.tmdbapp.data.network.model.MovieDetailsDto
import com.arthur.tmdbapp.data.network.response.PopularMoviesResponseDto
import com.arthur.tmdbapp.data.network.service.MovieService
import com.arthur.tmdbapp.data.source.MovieRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieService
) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Response<PopularMoviesResponseDto> {
        return apiService.getPopularMovies(page = page)
    }

    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsDto> {
        return apiService.getMovieDetails(movieId = movieId)
    }
}