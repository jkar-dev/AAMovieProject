package com.jkapps.aamovieproject

import android.app.Application
import com.jkapps.aamovieproject.data.MovieInteractor

class App : Application() {
    lateinit var interactor : MovieInteractor

    override fun onCreate() {
        super.onCreate()

        instance = this
        interactor = MovieInteractor(this)
    }

    companion object {
        var instance : App? = null
    }

}