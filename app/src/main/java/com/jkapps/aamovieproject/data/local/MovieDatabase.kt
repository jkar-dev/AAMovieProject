package com.jkapps.aamovieproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jkapps.aamovieproject.data.entity.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(MovieConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
}

const val DATABASE_NAME = "movies.db"