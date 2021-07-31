package com.example.rgc.flowrxandroid.data.domain.repository

import android.util.Log
import com.example.rgc.flowrxandroid.data.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val localDataSource : LocalDataSource,
                            private val remoteDataSource : RemoteDataSource) {

    private val TAG = "MovieRepositoryImpl"

    companion object {
        const val ITEMS_LIMIT = 10
        const val ITEMS_PAGE_SIZE = 20
    }

    fun getMovies() : Flow<List<Movie>> = localDataSource.getMovies()

    suspend fun requiredNewData(lastVisible : Int) {
        val size = localDataSource.size()
        Log.d(TAG, "size : ${size}")
        if(lastVisible >= size - ITEMS_LIMIT) {
            val page = size / ITEMS_PAGE_SIZE + 1
            Log.d(TAG, "page : ${page}")
            val newMovies = remoteDataSource.getMovies(page)
            if (newMovies != null) {
                localDataSource.saveMovies(newMovies)
            }
        }
    }
}










