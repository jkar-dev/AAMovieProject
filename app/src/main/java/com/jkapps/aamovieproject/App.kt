package com.jkapps.aamovieproject

import android.app.Application
import androidx.room.Room
import com.jkapps.aamovieproject.base.MovieRepository
import com.jkapps.aamovieproject.data.MovieRepositoryImpl
import com.jkapps.aamovieproject.data.local.DATABASE_NAME
import com.jkapps.aamovieproject.data.local.MovieDatabase
import com.jkapps.aamovieproject.data.remote.NetworkModule

class App : Application() {
    lateinit var repository: MovieRepository

    override fun onCreate() {
        super.onCreate()
        instance = this

        val db = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, DATABASE_NAME
        ).build()
        repository = MovieRepositoryImpl(db.movieDao(), NetworkModule.tmdbService)
    }

    companion object {
        var instance: App? = null
        const val TAG = " AppDebug"
    }
}