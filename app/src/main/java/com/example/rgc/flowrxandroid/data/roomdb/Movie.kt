package com.example.rgc.flowrxandroid.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(@PrimaryKey(autoGenerate = true) val id: Int,
                 val title:String,
                 val overview: String,
                 val posterPath: String?,
                 val voteAverage: Double) {
}
