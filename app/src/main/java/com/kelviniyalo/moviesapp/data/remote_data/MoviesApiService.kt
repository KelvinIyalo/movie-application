package com.kelviniyalo.moviesapp.data.remote_data

import com.kelviniyalo.moviesapp.BuildConfig
import com.kelviniyalo.moviesapp.common.Endpoints
import com.kelviniyalo.moviesapp.data.model.MoviesListResponse
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface MoviesApiService {

    @POST(Endpoints.MOVIES)
    suspend fun searchMoviesList(
        @Query("apikey") key: String = BuildConfig.API_KEY,
        @Query("s") title: String
    ): Response<MoviesListResponse>

    @POST(Endpoints.MOVIES)
    suspend fun getMoviesById(
        @Query("apikey") key: String = BuildConfig.API_KEY,
        @Query("i") title: String
    ): Response<MoviesResponse>
}