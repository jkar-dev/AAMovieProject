package com.jkapps.aamovieproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jkapps.aamovieproject.base.MovieRepository
import com.jkapps.aamovieproject.ui.list.MovieListViewModel

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when(modelClass) {
            MovieListViewModel::class.java -> return MovieListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
        }
    }
}