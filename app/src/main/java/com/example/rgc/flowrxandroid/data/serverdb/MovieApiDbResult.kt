package com.example.rgc.flowrxandroid.data.serverdb

import com.google.gson.annotations.SerializedName

data class MovieApiDbResult (
    val page: Int,
    val results : List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
