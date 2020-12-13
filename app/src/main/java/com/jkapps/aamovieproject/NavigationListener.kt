package com.jkapps.aamovieproject

import com.jkapps.aamovieproject.model.Movie

interface NavigationListener {
    fun pop()
    fun openDetails(movie : Movie)
}