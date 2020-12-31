package com.jkapps.aamovieproject.network.response

import com.jkapps.aamovieproject.data.entity.Actor
import com.squareup.moshi.Json

data class CreditsResponse(
    @field:Json(name = "cast")
    val cast : List<Actor>
)