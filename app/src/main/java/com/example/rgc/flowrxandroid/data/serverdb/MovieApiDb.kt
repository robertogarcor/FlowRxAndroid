package com.example.rgc.flowrxandroid.data.serverdb

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton MovieApiDb
 */
object MovieApiDb {

    private val URL_BASE  = "https://api.themoviedb.org/3/"

    // Interceptor to debug and get info request-response api
    private val httpOkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val retrofit: MovieApiService = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .client(httpOkHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().run {
            create(MovieApiService::class.java)
        }
}