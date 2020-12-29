package com.jkapps.aamovieproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jkapps.aamovieproject.App
import com.jkapps.aamovieproject.ui.details.FragmentMoviesDetails
import com.jkapps.aamovieproject.ui.list.FragmentMoviesList
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.entity.Movie

class MainActivity : AppCompatActivity(), NavigationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, FragmentMoviesList())
                .commit()
        }
    }

    override fun pop() {
        supportFragmentManager.popBackStack()
    }

    override fun openDetails(movie : Movie) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                FragmentMoviesDetails.instance(movie)
            )
            .addToBackStack(null)
            .commit()
    }
}