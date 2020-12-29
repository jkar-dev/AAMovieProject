package com.jkapps.aamovieproject

import com.jkapps.aamovieproject.data.entity.Movie


interface NavigationListener {
    fun pop()
    fun openDetails(movie : Movie)
}