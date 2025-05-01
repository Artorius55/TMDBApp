package com.arthur.tmdbapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arthur.tmdbapp.data.mapper.MovieDtoMapper
import com.arthur.tmdbapp.data.source.MovieRemoteDataSource
import com.arthur.tmdbapp.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: MovieDtoMapper
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1

        return try {
            val response = remoteDataSource.getPopularMovies(page = pageNumber)

            if (response.isSuccessful) {
                val responseBody = response.body()
                val movieDtos = responseBody?.results ?: emptyList()
                val domainMovies = mapper.mapList(movieDtos)

                val nextKey = if (
                    domainMovies.isEmpty() ||
                    pageNumber >= (responseBody?.totalPages ?: pageNumber)
                ) {
                    null
                } else {
                    pageNumber + 1
                }
                val prevKey = if (pageNumber == 1) null else pageNumber - 1

                LoadResult.Page(
                    data = domainMovies,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}