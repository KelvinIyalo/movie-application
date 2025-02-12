package com.kelviniyalo.moviesapp.common.response_handler

sealed class RepositoryResponse<out T> {
    class Success<out T>(val data:T): RepositoryResponse<T>()
    class Error(val error:String): RepositoryResponse<Nothing>()
    class ApiError(val apiError: String): RepositoryResponse<Nothing>()

}