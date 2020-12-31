package com.jkapps.aamovieproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkapps.aamovieproject.data.MovieInteractor
import com.jkapps.aamovieproject.data.Result
import com.jkapps.aamovieproject.data.entity.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(private val interactor: MovieInteractor) : ViewModel() {
    private val _movies : MutableLiveData<List<Movie>> = MutableLiveData()
    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    val movies : LiveData<List<Movie>> get() = _movies
    val isLoading : LiveData<Boolean> get() = _isLoading

    private var page = 1

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = interactor.loadMovies(page++)
                when (result) {
                    is Result.Success -> addToMoviesLiveData(result.output)
                    is Result.Error -> {}//TODO()
                }
            _isLoading.value = false
        }
    }

    private fun addToMoviesLiveData(newMovies : List<Movie>) {
        val list = mutableListOf<Movie>()
        _movies.value?.let { list.addAll(it) }
        _movies.value = list.apply { addAll(newMovies) }
    }
}