package com.jkapps.aamovieproject.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieListResponse(
    val results : List<MovieId>
)

@Serializable
class MovieId(val id: Int)
