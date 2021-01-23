package com.jkapps.aamovieproject.data

import com.jkapps.aamovieproject.base.MovieRepository
import com.jkapps.aamovieproject.data.remote.TmdbApiService
import com.jkapps.aamovieproject.data.remote.response.MovieId
import com.jkapps.aamovieproject.data.remote.response.MovieResponse

class MovieRepositoryImpl(private val service: TmdbApiService) : MovieRepository {

    override suspend fun loadMovie(page: Int): List<MovieId> {
        return service.getPopularMovies(page).results
    }

    override suspend fun loadDetails(id: Int): MovieResponse {
        return service.getMovieById(id)
    }

}