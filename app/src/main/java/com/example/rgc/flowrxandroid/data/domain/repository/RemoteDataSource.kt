package com.example.rgc.flowrxandroid.data.domain.repository

import com.example.rgc.flowrxandroid.data.domain.model.Movie

interface RemoteDataSource {
    suspend fun getMovies(page: Int) : List<Movie>?
}