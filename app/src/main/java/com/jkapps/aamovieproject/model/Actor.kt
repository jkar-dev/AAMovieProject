package com.jkapps.aamovieproject.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    @StringRes
    val name: Int,
    @DrawableRes
    val photo: Int
) : Parcelable