package com.jkapps.aamovieproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jkapps.aamovieproject.data.MovieInteractor
import com.jkapps.aamovieproject.ui.list.MovieListViewModel

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(private val interactor: MovieInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass) {
            MovieListViewModel::class.java -> return MovieListViewModel(interactor) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}