package com.jkapps.aamovieproject.network.response

import com.jkapps.aamovieproject.data.entity.Genre
import com.jkapps.aamovieproject.data.entity.Movie
import com.squareup.moshi.Json

data class MovieResponse(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPicture: String,
    @field:Json(name = "backdrop_path")
    val backdropPicture: String,
    @field:Json(name = "runtime")
    val runtime: Int,
    @field:Json(name = "genres")
    val genres: List<Genre>,
    @field:Json(name = "vote_average")
    val ratings: Float,
    @field:Json(name = "vote_count")
    val votesCount: Int,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "adult")
    val adult: Boolean
) {
    fun mapToMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            poster = "$IMAGE_BASE_URL$posterPicture",
            backdrop = "$IMAGE_BASE_URL$backdropPicture",
            runtime = runtime,
            genres = genres,
            ratings = ratings,
            numberOfRatings = votesCount,
            minimumAge = if (adult) 16 else 13
        )
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}

