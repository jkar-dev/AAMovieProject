package com.jkapps.aamovieproject.model

data class Movie(
    val title : String,
    val ageRestriction : Int,
    val genres : List<String>,
    val rate : Float,
    val numberOfReviewers : Int,
    val description : String,
    val actors : List<Actor>
)