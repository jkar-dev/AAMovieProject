package com.jkapps.aamovieproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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

    override fun openDetails() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, FragmentMoviesDetails())
            .addToBackStack(null)
            .commit()
    }
}