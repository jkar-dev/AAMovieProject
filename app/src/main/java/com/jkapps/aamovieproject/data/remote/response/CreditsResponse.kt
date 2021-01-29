package com.jkapps.aamovieproject.data.remote.response

import com.jkapps.aamovieproject.data.entity.Actor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
   @SerialName("cast")
    val cast : List<Actor>
)