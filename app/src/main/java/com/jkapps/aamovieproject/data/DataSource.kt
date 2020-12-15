package com.jkapps.aamovieproject.data

import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.model.Actor
import com.jkapps.aamovieproject.model.Movie

object DataSource {
    private val downey = Actor(R.string.downey_name, R.drawable.downey)
    private val evans = Actor(R.string.evans_name, R.drawable.evans)
    private val ruffalo = Actor(R.string.ruffalo_name, R.drawable.ruffalo)
    private val hemsworth = Actor(R.string.hemsworth_name, R.drawable.hemsworth)

    val movies = listOf(
        Movie(
            R.drawable.poster,
            R.drawable.origbackground,
            "Avengers: End Game",
            13,
            listOf("Action", "Fantasy", "Adventure"),
            4f,
            125,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(downey, evans, ruffalo, hemsworth),
            137
        ),
        Movie(
            R.drawable.poster,
            R.drawable.origbackground,
            "Avengers: End Game",
            13,
            listOf("Action", "Fantasy", "Adventure"),
            4f,
            125,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(downey, evans, ruffalo, hemsworth),
            137
        ),
        Movie(
            R.drawable.poster,
            R.drawable.origbackground,
            "Avengers: End Game",
            13,
            listOf("Action", "Fantasy", "Adventure"),
            4f,
            125,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(downey, evans, ruffalo, hemsworth),
            137
        ),
        Movie(
            R.drawable.poster,
            R.drawable.origbackground,
            "Avengers: End Game",
            13,
            listOf("Action", "Fantasy", "Adventure"),
            4f,
            125,
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            listOf(downey, evans, ruffalo, hemsworth),
            137
        )
    )
}