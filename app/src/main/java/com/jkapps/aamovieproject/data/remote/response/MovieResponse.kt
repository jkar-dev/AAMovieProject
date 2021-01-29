package com.jkapps.aamovieproject.data.remote.response

import com.jkapps.aamovieproject.data.entity.Genre
import com.jkapps.aamovieproject.data.entity.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("poster_path")
    val posterPicture: String?,
    @SerialName("backdrop_path")
    val backdropPicture: String?,
    @SerialName("runtime")
    val runtime: Int,
    @SerialName("genres")
    val genres: List<Genre>,
    @SerialName("vote_average")
    val ratings: Float,
    @SerialName("vote_count")
    val votesCount: Int,
    @SerialName("overview")
    val overview: String,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("credits")
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

