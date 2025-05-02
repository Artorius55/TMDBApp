package com.arthur.tmdbapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

/**
 * A composable that wraps a LazyPagingItems and handles the initial load/error states.
 * It shows a loading indicator while the data is being fetched,
 * an error message if the fetch fails,
 * and the actual content once the data is loaded successfully.
 */
@Composable
fun <T : Any> PaginatedListWrapper(
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    loadingContent: @Composable () -> Unit = {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    },
    errorContent: @Composable (error: Throwable, retry: () -> Unit) -> Unit = { error, retry ->
        ErrorItem(
            message = "Error al cargar: ${error.localizedMessage ?: "Error desconocido"}",
            onRetry = retry,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
    },
    content: @Composable (pagingItems: LazyPagingItems<T>) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (val refreshState = pagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                loadingContent()
            }
            is LoadState.Error -> {
                errorContent(refreshState.error, pagingItems::retry)
            }
            is LoadState.NotLoading -> {
                content(pagingItems)
            }
        }
    }
}