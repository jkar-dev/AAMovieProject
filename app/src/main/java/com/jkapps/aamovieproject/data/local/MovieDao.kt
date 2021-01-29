package com.jkapps.aamovieproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jkapps.aamovieproject.data.entity.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies : List<Movie>)
}