package com.jkapps.aamovieproject.data

import android.content.Context
import com.jkapps.aamovieproject.data.entity.Movie

class MovieInteractor(private val context : Context) {

    suspend fun loadMovies() : List<Movie> {
        return loadMovies(context)
    }
}