package com.kelviniyalo.moviesapp.common.response_handler

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class DisplayError(val error: String) : UiState<Nothing>()
}