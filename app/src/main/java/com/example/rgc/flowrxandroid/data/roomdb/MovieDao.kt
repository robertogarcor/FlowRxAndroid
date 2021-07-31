package com.example.rgc.flowrxandroid.data.roomdb

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies() : Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT count(id) FROM movies")
    fun countMovies() : Int

}