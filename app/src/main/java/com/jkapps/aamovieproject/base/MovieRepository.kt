package com.jkapps.aamovieproject.base

import com.jkapps.aamovieproject.data.remote.response.MovieId
import com.jkapps.aamovieproject.data.remote.response.MovieResponse

interface MovieRepository {
    suspend fun loadMovie(page : Int) : List<MovieId>
    suspend fun loadDetails(id : Int) : MovieResponse
}