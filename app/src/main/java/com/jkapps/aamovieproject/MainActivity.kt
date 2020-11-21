package com.jkapps.aamovieproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openDetailsActivity()
    }

    private fun openDetailsActivity() {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        startActivity(intent)
    }
}