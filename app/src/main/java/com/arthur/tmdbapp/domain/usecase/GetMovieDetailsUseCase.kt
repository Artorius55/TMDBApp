package com.arthur.tmdbapp.domain.usecase

import com.arthur.tmdbapp.data.repository.MovieRepository
import com.arthur.tmdbapp.domain.model.MovieDetails
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Result<MovieDetails> {
        return movieRepository.getMovieDetails(movieId)
    }
}