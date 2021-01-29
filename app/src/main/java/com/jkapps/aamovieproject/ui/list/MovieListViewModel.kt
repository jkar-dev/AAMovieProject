package com.jkapps.aamovieproject.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkapps.aamovieproject.base.Result
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.base.Event
import com.jkapps.aamovieproject.base.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies : MutableLiveData<List<Movie>> = MutableLiveData()
    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    private val _error : MutableLiveData<Event<Unit>> = MutableLiveData()

    val movies : LiveData<List<Movie>> get() = _movies
    val isLoading : LiveData<Boolean> get() = _isLoading
    val error : LiveData<Event<Unit>> get() = _error

    private var page = 1

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getMovies().collect {
                when (it) {
                    is Result.Success -> addToMoviesLiveData(it.output)
                    is Result.Error -> handleError()
                }
            }
            _isLoading.value = false
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.loadMoreMovies(++page).collect {
                when (it) {
                    is Result.Success -> addToMoviesLiveData(it.output)
                    is Result.Error -> handleError()
                }
            }
            _isLoading.value = false
        }
    }

    private fun addToMoviesLiveData(newMovies : List<Movie>) {
        val list = mutableListOf<Movie>()
        _movies.value?.let { list.addAll(it) }
        _movies.value = list.apply { addAll(newMovies) }
    }

    private fun handleError() {
        _error.value = Event(Unit)
        page--
    }
}