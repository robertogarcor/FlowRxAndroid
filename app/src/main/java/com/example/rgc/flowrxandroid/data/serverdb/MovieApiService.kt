package com.example.rgc.flowrxandroid.data.serverdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getListMoviesPopular(
        @Query("api_key") apiKey : String,
        @Query("page") page: Int
    ) : Call<MovieApiDbResult>

    /*
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getListMoviesPopular(
        @Query("api_key") apiKey : String,
        @Query("page") page: Int
    ) : MovieApiDbResult
    */


}