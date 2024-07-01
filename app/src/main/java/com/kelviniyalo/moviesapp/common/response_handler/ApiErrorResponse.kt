package com.kelviniyalo.moviesapp.common.response_handler

data class ApiErrorResponse(
    val message: String? = null,
    val validationMessages: List<String>? = null,
    val errorCode: Int? = null
)