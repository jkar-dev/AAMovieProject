package com.jkapps.aamovieproject.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Actor(
    @StringRes
    val name: Int,
    @DrawableRes
    val image: Int
)