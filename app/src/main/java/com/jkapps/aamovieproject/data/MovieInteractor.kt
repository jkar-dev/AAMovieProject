package com.jkapps.aamovieproject.data

import com.jkapps.aamovieproject.data.entity.Movie

class MovieInteractor(private val repository: MovieRepository) {

    suspend fun loadMovies(page : Int = 1): Result<List<Movie>> {
        return try {
            val ids = repository.loadMovie(page)
            val listOfMovie = mutableListOf<Movie>()
            ids.forEach {
                val movie = repository.loadDetails(it.id).mapToMovie()
                listOfMovie.add(movie)
            }
            Result.Success(listOfMovie)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}