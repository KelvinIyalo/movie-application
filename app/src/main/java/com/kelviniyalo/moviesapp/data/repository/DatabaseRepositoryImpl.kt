package com.kelviniyalo.moviesapp.data.repository

import androidx.lifecycle.LiveData
import com.kelviniyalo.moviesapp.data.local_data.DatabaseDao
import com.kelviniyalo.moviesapp.data.model.MoviesResponse
import com.kelviniyalo.moviesapp.domain.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val databaseDao: DatabaseDao) :
    DatabaseRepository {

    override suspend fun saveToDb(entity: MoviesResponse): Long {
        return databaseDao.insert(entity = entity)
    }

    override fun getTransactionsFromDb(): LiveData<List<MoviesResponse>> {
        return databaseDao.getAllMovies()
    }

    override suspend fun deleteAllFromDb() {
        return databaseDao.deleteAll()
    }
}