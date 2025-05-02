package com.arthur.tmdbapp.ui.extensions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.arthur.tmdbapp.ui.components.ErrorItem

fun <T : Any> LazyGridScope.handlePagingAppendStateForGrid(
    pagingItems: LazyPagingItems<T>
) {
    when (val appendState = pagingItems.loadState.append) {
        is LoadState.Loading -> {
            item(span = { GridItemSpan(maxLineSpan) }, key = "append_loading_grid") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is LoadState.Error -> {
            item(span = { GridItemSpan(maxLineSpan) }, key = "append_error_grid") {
                ErrorItem(
                    message = "Error al cargar más: ${appendState.error.localizedMessage ?: "Error desconocido"}",
                    onRetry = pagingItems::retry
                )
            }
        }
        is LoadState.NotLoading -> {
            if (appendState.endOfPaginationReached && pagingItems.itemCount > 0) {
                item(span = { GridItemSpan(maxLineSpan) }, key = "append_end_of_list_grid") {
                    Text(
                        text = "Has llegado al final",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}