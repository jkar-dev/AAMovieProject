package com.jkapps.aamovieproject.data.remote.response

import com.jkapps.aamovieproject.data.entity.Actor
import com.squareup.moshi.Json

data class CreditsResponse(
    @field:Json(name = "cast")
    val cast : List<Actor>
)