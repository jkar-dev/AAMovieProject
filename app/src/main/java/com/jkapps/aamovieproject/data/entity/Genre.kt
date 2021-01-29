package com.jkapps.aamovieproject.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Genre(val id: Int, val name: String) : Parcelable