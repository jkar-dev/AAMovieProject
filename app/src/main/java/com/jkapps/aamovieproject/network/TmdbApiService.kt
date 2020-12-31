package com.jkapps.aamovieproject.network

import com.jkapps.aamovieproject.network.response.MovieListResponse
import com.jkapps.aamovieproject.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int) : MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int): MovieResponse
}