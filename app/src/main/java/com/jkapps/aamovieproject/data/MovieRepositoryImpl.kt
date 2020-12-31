package com.jkapps.aamovieproject.data

import com.jkapps.aamovieproject.network.TmdbApiService
import com.jkapps.aamovieproject.network.response.MovieId
import com.jkapps.aamovieproject.network.response.MovieResponse

class MovieRepositoryImpl(private val service: TmdbApiService) : MovieRepository {

    override suspend fun loadMovie(page: Int): List<MovieId> {
        return service.getPopularMovies(page).results
    }

    override suspend fun loadDetails(id: Int): MovieResponse {
        return service.getMovieById(id)
    }

}