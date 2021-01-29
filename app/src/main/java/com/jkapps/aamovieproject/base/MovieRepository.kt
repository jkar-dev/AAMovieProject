package com.jkapps.aamovieproject.base

import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.data.remote.response.MovieId
import com.jkapps.aamovieproject.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies() : Flow<Result<List<Movie>>>
    suspend fun loadMoreMovies(page : Int) : Flow<Result<List<Movie>>>
    suspend fun loadDetails(id : Int) : MovieResponse
    suspend fun loadMoviesIds(page: Int) : List<MovieId>
}