package com.kelviniyalo.moviesapp.data.model

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String,
    val Image:ByteArray? = null
)