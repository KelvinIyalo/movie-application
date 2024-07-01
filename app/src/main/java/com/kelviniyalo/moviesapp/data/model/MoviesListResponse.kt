package com.kelviniyalo.moviesapp.data.model

data class MoviesListResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)