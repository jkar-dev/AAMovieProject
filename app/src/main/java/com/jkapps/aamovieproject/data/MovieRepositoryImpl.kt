package com.jkapps.aamovieproject.data

import android.util.Log
import com.jkapps.aamovieproject.App
import com.jkapps.aamovieproject.base.MovieRepository
import com.jkapps.aamovieproject.base.ResponseResult
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.data.local.MovieDao
import com.jkapps.aamovieproject.data.remote.TmdbApiService
import com.jkapps.aamovieproject.data.remote.response.MovieId
import com.jkapps.aamovieproject.data.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val movieDao: MovieDao, private val movieService: TmdbApiService) : MovieRepository {

    override suspend fun getMovies(isFirstLoad: Boolean, page: Int): Flow<ResponseResult<List<Movie>>> = flow {
        try {
            Log.i(App.TAG, "IsFirstLoad = $isFirstLoad")
            Log.i(App.TAG, "Page = $page")
            if (isFirstLoad) {
                val cache = movieDao.getMovies()
                emit(ResponseResult.Success(cache))
            }
            val movies = loadMoviesWithAllData(page)
            emit(ResponseResult.Success(movies))
            movieDao.insertMovies(movies)
        } catch (e: Exception) {
            emit(ResponseResult.Error(e))
        }
    }

    override suspend fun loadDetails(id: Int): MovieResponse {
        return movieService.getMovieById(id)
    }

    override suspend fun loadMoviesIds(page: Int): List<MovieId> {
        return movieService.getPopularMovies(page).results
    }

    private suspend fun loadMoviesWithAllData(page: Int): List<Movie> {
        return loadMoviesIds(page).map {
            loadDetails(it.id).mapToMovie()
        }
    }
}