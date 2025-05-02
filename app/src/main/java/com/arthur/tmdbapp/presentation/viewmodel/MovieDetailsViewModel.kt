package com.arthur.tmdbapp.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.tmdbapp.domain.model.MovieDetails
import com.arthur.tmdbapp.domain.usecase.GetMovieDetailsUseCase
import com.arthur.tmdbapp.presentation.state.MovieDetailsUiState
import com.arthur.tmdbapp.ui.navigation.NavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val uiState: StateFlow<MovieDetailsUiState> = _uiState.asStateFlow()

    private val movieId: Int = checkNotNull(savedStateHandle[NavArgs.MOVIE_ID]) {
        "Arg ${NavArgs.MOVIE_ID} cannot be null"
    }

    init {
        fetchMovieDetails()
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            _uiState.value = MovieDetailsUiState.Loading
            val result: Result<MovieDetails> = getMovieDetailsUseCase(movieId)
            _uiState.update {
                result.fold(
                    onSuccess = { details -> MovieDetailsUiState.Success(details) },
                    onFailure = { error ->
                        MovieDetailsUiState.Error(error.localizedMessage ?: "Unknown error")
                    }
                )
            }
        }
    }

    fun retry() {
        fetchMovieDetails()
    }
}