package com.example.rgc.flowrxandroid.data.domain.repository

import com.example.rgc.flowrxandroid.data.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getMovies() : Flow<List<Movie>>
    suspend fun saveMovies(movies : List<Movie>)
    suspend fun size() : Int
}