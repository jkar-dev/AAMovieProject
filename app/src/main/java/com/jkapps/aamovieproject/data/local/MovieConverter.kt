package com.jkapps.aamovieproject.data.local

import androidx.room.TypeConverter
import com.jkapps.aamovieproject.data.entity.Actor
import com.jkapps.aamovieproject.data.entity.Genre
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MovieConverter {

    @TypeConverter
    fun fromListOfActors(actors : List<Actor>) : String {
        return Json.encodeToString(actors)
    }

    @TypeConverter
    fun toListOfActors(actorsString : String) : List<Actor> {
        return Json.decodeFromString(actorsString)
    }

    @TypeConverter
    fun fromListOfGenres(genres : List<Genre>) : String {
        return Json.encodeToString(genres)
    }

    @TypeConverter
    fun toListOfGenres(genresString : String) : List<Genre> {
        return Json.decodeFromString(genresString)
    }
}