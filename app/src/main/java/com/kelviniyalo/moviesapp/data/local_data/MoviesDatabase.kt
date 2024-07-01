package com.kelviniyalo.moviesapp.data.local_data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kelviniyalo.moviesapp.data.model.MoviesResponse

@Database(entities = [MoviesResponse::class], version = 2)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

}