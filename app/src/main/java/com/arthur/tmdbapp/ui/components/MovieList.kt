package com.arthur.tmdbapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.arthur.tmdbapp.domain.model.Movie
import com.arthur.tmdbapp.ui.extensions.handlePagingAppendStateForGrid

@Composable
fun MoviesList(
    pagingItems: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(8.dp),
    itemContent: @Composable (movie: Movie) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement
    ) {
        items(
            count = pagingItems.itemCount,
            key = pagingItems.itemKey { movie -> movie.id },
            contentType = pagingItems.itemContentType { "Movie" }
        ) { index ->
            val movie: Movie? = pagingItems[index]
            movie?.let { domainMovie ->
                itemContent(domainMovie)
            }
        }

        handlePagingAppendStateForGrid(pagingItems = pagingItems)

        if (pagingItems.loadState.refresh is LoadState.NotLoading &&
            pagingItems.loadState.append.endOfPaginationReached &&
            pagingItems.itemCount == 0) {
            item(span = { GridItemSpan(maxLineSpan) }, key = "empty_list_placeholder_grid") {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 50.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron películas populares.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}