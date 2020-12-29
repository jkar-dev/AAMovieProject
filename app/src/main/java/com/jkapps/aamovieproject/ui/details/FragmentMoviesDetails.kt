package com.jkapps.aamovieproject.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.adapters.ActorsAdapter
import com.jkapps.aamovieproject.data.entity.Movie

class FragmentMoviesDetails : Fragment() {
    private var navListener: NavigationListener? = null
    private val adapter = ActorsAdapter()
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this)[DetailsViewModel::class.java] }

    private var ivPoster: ImageView? = null
    private var tvAge: TextView? = null
    private var tvTitle: TextView? = null
    private var tvGenres: TextView? = null
    private var tvReviews: TextView? = null
    private var rbRate: RatingBar? = null
    private var tvDescription: TextView? = null
    private var tvCast: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        setUpRecycler()
        setMovie()

        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            navListener?.pop()
        }

        viewModel.movie.observe(this) {
            fillWithInformation(it)
        }
    }

    private fun initViews(view: View) {
        ivPoster = view.findViewById(R.id.iv_poster)
        tvAge = view.findViewById(R.id.tv_age_restriction)
        tvTitle = view.findViewById(R.id.tv_title)
        tvGenres = view.findViewById(R.id.tv_genres)
        tvReviews = view.findViewById(R.id.tv_reviews)
        rbRate = view.findViewById(R.id.rb_rate)
        tvDescription = view.findViewById(R.id.tv_description)
        tvCast = view.findViewById(R.id.tv_cast)
    }

    private fun setUpRecycler() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.rv_actors)
        recycler.apply {
            adapter = this@FragmentMoviesDetails.adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setMovie() {
        arguments?.getParcelable<Movie>(KEY_MOVIE)?.let {
            viewModel.setMovie(it)
        }
    }

    private fun fillWithInformation(movie: Movie) {
        Glide.with(requireContext()).load(movie.backdrop).into(ivPoster!!)
        tvAge?.text = getString(R.string.age_format, movie.minimumAge)
        tvTitle?.text = movie.title
        tvGenres?.text = movie.genres.joinToString { genre -> genre.name }
        tvReviews?.text = getString(R.string.reviews_format, movie.numberOfRatings)
        rbRate?.rating = movie.ratings / 2
        tvDescription?.text = movie.overview

        if (movie.actors.isEmpty()) tvCast?.visibility = View.GONE
        else adapter.setActors(movie.actors)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        ivPoster = null
        tvAge = null
        tvTitle = null
        tvGenres = null
        tvReviews = null
        rbRate = null
        tvDescription = null
        tvCast = null
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