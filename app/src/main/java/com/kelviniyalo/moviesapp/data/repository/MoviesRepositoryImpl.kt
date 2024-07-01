package com.kelviniyalo.moviesapp.data.repository

import com.kelviniyalo.moviesapp.common.response_handler.RepositoryResponse
import com.kelviniyalo.moviesapp.common.response_handler.exceptionHandler
import com.kelviniyalo.moviesapp.common.response_handler.getCallResponseState
import com.kelviniyalo.moviesapp.data.model.MoviesListResponse
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import com.kelviniyalo.moviesapp.data.remote_data.MoviesApiService
import com.kelviniyalo.moviesapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val moviesApiService: MoviesApiService): MoviesRepository {

    override suspend fun searchMovies(tittle: String): RepositoryResponse<MoviesListResponse> {
        return try {
            val result = moviesApiService.searchMoviesList(title = tittle)
            getCallResponseState(result)
        } catch (exception: Exception) {
            return exceptionHandler(exception)
        }
    }

    override suspend fun getMovieById(tittle: String): RepositoryResponse<MoviesResponse> {
        return try {
            val result = moviesApiService.getMoviesById(title = tittle)
            getCallResponseState(result)
        } catch (exception: Exception) {
            return exceptionHandler(exception)
        }
    }


}