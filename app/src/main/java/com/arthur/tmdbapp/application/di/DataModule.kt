package com.arthur.tmdbapp.application.di

import com.arthur.tmdbapp.data.repository.MovieRepository
import com.arthur.tmdbapp.data.repository.impl.MovieRepositoryImpl
import com.arthur.tmdbapp.data.source.MovieRemoteDataSource
import com.arthur.tmdbapp.data.source.impl.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(
        impl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        impl: MovieRepositoryImpl
    ): MovieRepository
}