package com.jkapps.aamovieproject.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jkapps.aamovieproject.data.entity.Movie

class DetailsViewModel : ViewModel() {
    private val _movie : MutableLiveData<Movie> = MutableLiveData()
    val movie : LiveData<Movie> get() = _movie

    fun setMovie(movie: Movie) {
        if (_movie.value == null) _movie.value = movie
    }
}