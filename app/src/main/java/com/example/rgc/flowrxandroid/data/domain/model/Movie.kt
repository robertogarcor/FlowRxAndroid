package com.example.rgc.flowrxandroid.data.domain.model

import java.io.Serializable

data class Movie(
                 val id: Int,
                 val title: String,
                 val overview: String,
                 val posterPath: String?,
                 val voteAverage: Double) : Serializable
