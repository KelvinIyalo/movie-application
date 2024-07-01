package com.kelviniyalo.moviesapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MoviesDatabase")
data class MoviesResponse(
    @PrimaryKey(autoGenerate = false)
    val imdbID: String,
    val Actors: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Plot: String,
    val Poster: String,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Writer: String,
    val Year: String,
    val Image:ByteArray? = null
):Serializable


