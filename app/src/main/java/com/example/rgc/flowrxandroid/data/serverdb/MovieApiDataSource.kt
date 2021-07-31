package com.example.rgc.flowrxandroid.data.serverdb

import com.example.rgc.flowrxandroid.data.domain.model.Movie
import com.example.rgc.flowrxandroid.data.domain.repository.RemoteDataSource
import com.example.rgc.flowrxandroid.data.toDomainMovie


class MovieApiDataSource(var apiKey: String) : RemoteDataSource {

   /*
   override suspend fun getMovies(page: Int): List<Movie> =
        MovieApiDb.retrofit
            .getListMoviesPopular(apiKey, page)
            .results.map { it.toDomainMovie() }
   */


   override suspend fun getMovies(page: Int): List<Movie>? {
        val call = MovieApiDb.retrofit.getListMoviesPopular(apiKey, page)
        val res = call.execute().body()?.results
        var results : List<Movie>? = null
        if (res != null) {
            results = res.map { it.toDomainMovie() }
        }
        return results
   }
}
