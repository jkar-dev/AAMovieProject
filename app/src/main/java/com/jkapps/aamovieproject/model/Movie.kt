package com.jkapps.aamovieproject.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @DrawableRes
    val image : Int,
    @DrawableRes
    val poster : Int,
    val title : String,
    private val _ageRestriction : Int,
    val genres : List<String>,
    val rate : Float,
    private val _numberOfReviewers : Int,
    val description : String,
    val actors : List<Actor>,
    private val _duration : Int
) : Parcelable {
    val numberOfReviewers : String get() = "$_numberOfReviewers Reviews"
    val ageRestriction : String get() = "$_ageRestriction+"
    val duration : String get() = "$_duration MIN"
}