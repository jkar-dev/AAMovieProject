package com.jkapps.aamovieproject.network.response

import com.squareup.moshi.Json

data class MovieListResponse(
    val results : List<MovieId>
)

class MovieId(
    @Json(name = "id")
    val id: Int)
