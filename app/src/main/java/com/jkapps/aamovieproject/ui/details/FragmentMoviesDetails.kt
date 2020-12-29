package com.jkapps.aamovieproject.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jkapps.aamovieproject.adapters.ActorsAdapter
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.entity.Movie

class FragmentMoviesDetails : Fragment() {
    private var navListener: NavigationListener? = null
    private var movie: Movie? = null
    private val adapter = ActorsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(KEY_MOVIE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillInformation(view)

        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            navListener?.pop()
        }
    }

    private fun fillInformation(view: View) {
        movie?.let {
            val ivPoster: ImageView = view.findViewById(R.id.iv_poster)
            val tvAge: TextView = view.findViewById(R.id.tv_age_restriction)
            val tvTitle: TextView = view.findViewById(R.id.tv_title)
            val tvGenres: TextView = view.findViewById(R.id.tv_genres)
            val tvReviews: TextView = view.findViewById(R.id.tv_reviews)
            val rbRate: RatingBar = view.findViewById(R.id.rb_rate)
            val tvDescription: TextView = view.findViewById(R.id.tv_description)

            Glide.with(view).load(it.backdrop).into(ivPoster)
            tvAge.text = getString(R.string.age_format, it.minimumAge)
            tvTitle.text = it.title
            tvGenres.text = it.genres.joinToString { genre -> genre.name }
            tvReviews.text = getString(R.string.reviews_format, it.numberOfRatings)
            rbRate.rating = it.ratings / 2
            tvDescription.text = it.overview

            if (it.actors.isEmpty()) {
                view.findViewById<View>(R.id.tv_cast).apply { isVisible = false }
            } else {
                adapter.setActors(it.actors)
                setUpRecycler()
            }
        }
    }

    private fun setUpRecycler() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.rv_actors)
        recycler.apply {
            adapter = this@FragmentMoviesDetails.adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) navListener = context
    }

    override fun onDetach() {
        super.onDetach()
        navListener = null
    }

    companion object {
        fun instance(movie: Movie): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails().apply {
                arguments = Bundle().apply { putParcelable(KEY_MOVIE, movie) }
            }
            return fragment
        }

        private const val KEY_MOVIE = "movie"
    }
}