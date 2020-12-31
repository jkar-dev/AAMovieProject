package com.jkapps.aamovieproject.data

import com.jkapps.aamovieproject.network.response.MovieId
import com.jkapps.aamovieproject.network.response.MovieResponse

interface MovieRepository {
    suspend fun loadMovie(page : Int) : List<MovieId>
    suspend fun loadDetails(id : Int) : MovieResponse
}