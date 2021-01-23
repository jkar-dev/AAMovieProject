package com.jkapps.aamovieproject

import android.app.Application
import com.jkapps.aamovieproject.data.MovieInteractor
import com.jkapps.aamovieproject.data.MovieRepositoryImpl
import com.jkapps.aamovieproject.data.remote.NetworkModule

class App : Application() {
    lateinit var interactor : MovieInteractor

    override fun onCreate() {
        super.onCreate()

        instance = this
        val repository = MovieRepositoryImpl(NetworkModule.tmdbService)
        interactor = MovieInteractor(repository)
    }

    companion object {
        var instance : App? = null
    }
}