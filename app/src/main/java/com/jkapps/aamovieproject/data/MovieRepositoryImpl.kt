package com.jkapps.aamovieproject.data

import android.util.Log
import com.jkapps.aamovieproject.base.MovieRepository
import com.jkapps.aamovieproject.base.Result
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.data.local.MovieDao
import com.jkapps.aamovieproject.data.remote.TmdbApiService
import com.jkapps.aamovieproject.data.remote.response.MovieId
import com.jkapps.aamovieproject.data.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(private val movieDao: MovieDao, private val movieService: TmdbApiService) : MovieRepository {

    override suspend fun getMovies(): Flow<Result<List<Movie>>> = flow {
        try {
            val cache = movieDao.getMovies()
            emit(Result.Success(cache))
            val movies = loadMoviesWithAllData(page = 1)
            emit(Result.Success(movies))
            movieDao.insertMovies(movies)
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override suspend fun loadMoreMovies(page: Int): Flow<Result<List<Movie>>> = flow {
        try {
            val movies = loadMoviesWithAllData(page)
            emit(Result.Success(movies))
            movieDao.insertMovies(movies)
        } catch (e: Exception) {
            emit(Result.Error(e))
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