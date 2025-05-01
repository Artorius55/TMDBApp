package com.arthur.tmdbapp.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arthur.tmdbapp.data.mapper.MovieDetailsDtoMapper
import com.arthur.tmdbapp.data.mapper.MovieDtoMapper
import com.arthur.tmdbapp.data.paging.MoviePagingSource
import com.arthur.tmdbapp.data.repository.MovieRepository
import com.arthur.tmdbapp.data.source.MovieRemoteDataSource
import com.arthur.tmdbapp.domain.model.Movie
import com.arthur.tmdbapp.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val movieDtoMapper: MovieDtoMapper,
    private val movieDetailsDtoMapper: MovieDetailsDtoMapper
) : MovieRepository {

    override fun getPopularMoviesStream(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(
                    remoteDataSource = remoteDataSource,
                    mapper = movieDtoMapper
                )
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> {
        return try {
            val response = remoteDataSource.getMovieDetails(movieId = movieId)
            if (response.isSuccessful && response.body() != null) {
                val domainDetails = movieDetailsDtoMapper.map(response.body()!!)
                Result.success(domainDetails)
            } else {
                Result.failure(
                    HttpException(response)
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}