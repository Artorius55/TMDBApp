package com.arthur.tmdbapp.data.repository

import androidx.paging.PagingData
import com.arthur.tmdbapp.domain.model.Movie
import com.arthur.tmdbapp.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMoviesStream(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>
}