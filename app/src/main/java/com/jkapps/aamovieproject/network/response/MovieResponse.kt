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
    val adult: Boolean,
    @field:Json(name = "credits")
    val credits : CreditsResponse
) {
    fun mapToMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            overview = overview,
            poster = "$POSTER_URL$posterPicture",
            backdrop = "$BACKDROP_URL$backdropPicture",
            runtime = runtime,
            genres = genres,
            ratings = ratings,
            numberOfRatings = votesCount,
            minimumAge = if (adult) 16 else 13,
            actors = credits.cast.take(10).map { it.copy(picture = "$PROFILE_URL${it.picture}") }
        )
    }

    companion object {
        private const val POSTER_URL = "https://image.tmdb.org/t/p/w342"
        private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
        private const val PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    }
}

