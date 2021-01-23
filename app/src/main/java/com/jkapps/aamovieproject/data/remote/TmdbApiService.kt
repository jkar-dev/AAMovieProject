package com.jkapps.aamovieproject.data.remote

import com.jkapps.aamovieproject.data.remote.response.MovieListResponse
import com.jkapps.aamovieproject.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int) : MovieListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") id: Int,
                             @Query("append_to_response") append : String = "credits"): MovieResponse
}