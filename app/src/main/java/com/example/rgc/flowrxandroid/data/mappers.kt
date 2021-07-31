package com.example.rgc.flowrxandroid.data

import com.example.rgc.flowrxandroid.data.domain.model.Movie
import com.example.rgc.flowrxandroid.data.roomdb.Movie as RoomMovie
import com.example.rgc.flowrxandroid.data.serverdb.Movie as ServerMovie

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        overview,
        posterPath,
        voteAverage
    )

fun Movie.toRoomMovie(): RoomMovie =
    RoomMovie(
        id,
        title,
        overview,
        posterPath,
        voteAverage
    )

fun RoomMovie.toDomainMovie(): Movie =
    Movie(
        id,
        title,
        overview,
        posterPath,
        voteAverage
    )