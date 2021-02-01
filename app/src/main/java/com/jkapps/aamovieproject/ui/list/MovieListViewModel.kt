package com.jkapps.aamovieproject.ui.list

import android.util.Log
import androidx.lifecycle.*
import com.jkapps.aamovieproject.App
import com.jkapps.aamovieproject.base.ResponseResult
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.base.Event
import com.jkapps.aamovieproject.base.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Event<Unit>> = MutableLiveData()

    val movies: LiveData<List<Movie>> get() = _movies
    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<Event<Unit>> get() = _error

    private var page = 0

    init {
        loadMovies()
    }

    fun loadMovies() {
        if (isLoading.value == true) return
        _isLoading.value = true
        viewModelScope.launch {
            val isFirstLoad = movies.value == null
            repository.getMovies(isFirstLoad, ++page).collect {
                when (it) {
                    is ResponseResult.Success -> {
                        Log.i(App.TAG, "Success. Size: ${it.output.size}")
                        _isLoading.value = false
                        addToMoviesLiveData(it.output)

                        // set page after getting from cache
                        if (it.output.size > 20) page = it.output.size / MOVIES_PER_PAGE
                    }
                    is ResponseResult.Error -> {
                        Log.i(App.TAG, "Failure: ${it.exception}")
                        _isLoading.value = false
                        handleError()
                    }
                }
            }
        }
    }

    private fun addToMoviesLiveData(newMovies: List<Movie>) {
        val list = mutableListOf<Movie>()
        _movies.value?.let { list.addAll(it) }
        _movies.value = list.apply { addAll(newMovies) }
    }

    private fun handleError() {
        _error.value = Event(Unit)
        //page--
    }

    companion object {
        private const val MOVIES_PER_PAGE = 20
    }
}