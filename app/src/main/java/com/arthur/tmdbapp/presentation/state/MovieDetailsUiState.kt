package com.arthur.tmdbapp.presentation.state

import com.arthur.tmdbapp.domain.model.MovieDetails

sealed interface MovieDetailsUiState {
    data object Loading : MovieDetailsUiState
    data class Success(val details: MovieDetails) : MovieDetailsUiState
    data class Error(val message: String) : MovieDetailsUiState
}