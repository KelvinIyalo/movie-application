package com.kelviniyalo.moviesapp.domain.repository

import com.kelviniyalo.moviesapp.common.response_handler.RepositoryResponse
import com.kelviniyalo.moviesapp.data.model.MoviesListResponse
import com.kelviniyalo.moviesapp.data.model.MoviesResponse

interface MoviesRepository {

    suspend fun searchMovies(tittle:String): RepositoryResponse<MoviesListResponse>
    suspend fun getMovieById(tittle:String): RepositoryResponse<MoviesResponse>
}