package com.arthur.tmdbapp.domain.usecase

import androidx.paging.PagingData
import com.arthur.tmdbapp.data.repository.MovieRepository
import com.arthur.tmdbapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return movieRepository.getPopularMoviesStream()
    }
}