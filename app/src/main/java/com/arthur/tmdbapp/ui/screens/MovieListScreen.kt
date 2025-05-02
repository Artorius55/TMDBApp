package com.arthur.tmdbapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.*
import com.arthur.tmdbapp.domain.model.Movie
import com.arthur.tmdbapp.presentation.viewmodel.MovieViewModel
import com.arthur.tmdbapp.ui.components.MovieItem
import com.arthur.tmdbapp.ui.components.MoviesList
import com.arthur.tmdbapp.ui.components.PaginatedListWrapper

@Composable
fun MovieListScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {
    val lazyMovieItems: LazyPagingItems<Movie> = viewModel.popularMovies.collectAsLazyPagingItems()
    PaginatedListWrapper(pagingItems = lazyMovieItems) { currentPagingItems ->
        MoviesList(
            pagingItems = currentPagingItems,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            itemContent = { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onMovieClick(movie.id) }
                )
            }
        )
    }
}