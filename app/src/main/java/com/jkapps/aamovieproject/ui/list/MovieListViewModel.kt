package com.jkapps.aamovieproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkapps.aamovieproject.data.MovieInteractor
import com.jkapps.aamovieproject.data.Result
import com.jkapps.aamovieproject.data.entity.Movie
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(private val interactor: MovieInteractor) : ViewModel() {
    private val _movies : MutableLiveData<List<Movie>> = MutableLiveData()
    //private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    val movies : LiveData<List<Movie>> get() = _movies
    //val isLoading : LiveData<Boolean> get() = _isLoading

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            val result = interactor.loadMovies()
                when (result) {
                    is Result.Success -> _movies.value = result.output
                    is Result.Error -> {}//TODO()
                }

        }
    }
}