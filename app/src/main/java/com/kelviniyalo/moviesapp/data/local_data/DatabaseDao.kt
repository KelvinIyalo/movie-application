package com.kelviniyalo.moviesapp.data.local_data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kelviniyalo.moviesapp.data.model.MoviesResponse

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MoviesResponse): Long

    @Query("DELETE FROM MoviesDatabase")
    suspend fun deleteAll()

    @Query(
        """
        SELECT * FROM MoviesDatabase ORDER BY imdbID DESC
        """
    )
    fun getAllMovies(): LiveData<List<MoviesResponse>>
}