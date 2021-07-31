package com.example.rgc.flowrxandroid

import android.app.Application
import androidx.room.Room
import com.example.rgc.flowrxandroid.data.roomdb.MovieDatabase

class MoviesApp : Application() {

    lateinit var roomdb: MovieDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        // Get Instance Room DB
        roomdb = Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "movies-db"
        ).build()
    }

}