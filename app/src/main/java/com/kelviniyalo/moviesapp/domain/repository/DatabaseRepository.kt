package com.kelviniyalo.moviesapp.domain.repository

import androidx.lifecycle.LiveData
import com.kelviniyalo.moviesapp.data.model.MoviesResponse

interface DatabaseRepository {

    suspend fun saveToDb(entity: MoviesResponse): Long

    fun getTransactionsFromDb(): LiveData<List<MoviesResponse>>

    suspend fun deleteAllFromDb()
}