package com.jkapps.aamovieproject.model

import androidx.annotation.DrawableRes

data class Movie(
    @DrawableRes
    val image : Int,
    val title : String,
    private val _ageRestriction : Int,
    val genres : List<String>,
    val rate : Float,
    private val _numberOfReviewers : Int,
    val description : String,
    val actors : List<Actor>,
    private val _duration : Int
) {
    val numberOfReviewers : String get() = "$_numberOfReviewers Reviewers"
    val ageRestriction : String get() = "$_ageRestriction+"
    val duration : String get() = "$_duration MIN"
}