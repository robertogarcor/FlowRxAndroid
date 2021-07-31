package com.example.rgc.flowrxandroid.data.roomdb

import com.example.rgc.flowrxandroid.data.domain.model.Movie
import com.example.rgc.flowrxandroid.data.domain.repository.LocalDataSource
import com.example.rgc.flowrxandroid.data.toDomainMovie
import com.example.rgc.flowrxandroid.data.toRoomMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRoomDataSource(db : MovieDatabase) : LocalDataSource {

    private val movieDao =  db.movieDao()

    override fun getMovies(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { movies -> movies.map { it.toDomainMovie() }}
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.insertMovies(movies.map { it.toRoomMovie() })
    }

    override suspend fun size(): Int = movieDao.countMovies()


}