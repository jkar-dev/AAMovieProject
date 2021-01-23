package com.jkapps.aamovieproject.data.remote.response

import com.squareup.moshi.Json

data class MovieListResponse(
    val results : List<MovieId>
)

class MovieId(
    @Json(name = "id")
    val id: Int)
