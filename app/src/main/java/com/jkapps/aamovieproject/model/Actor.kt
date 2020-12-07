package com.jkapps.aamovieproject.model

import androidx.annotation.DrawableRes

data class Actor(
    val name: String,
    @DrawableRes
    val image: Int
)