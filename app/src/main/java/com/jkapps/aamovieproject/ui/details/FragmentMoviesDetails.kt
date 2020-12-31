package com.jkapps.aamovieproject.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.adapter.ActorsAdapter
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.databinding.FragmentMoviesDetailsBinding

class FragmentMoviesDetails : Fragment() {
    private val adapter = ActorsAdapter()
    private val viewModel: DetailsViewModel by lazy { ViewModelProvider(this)[DetailsViewModel::class.java] }
    private var binding : FragmentMoviesDetailsBinding? = null
    private var navListener: NavigationListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        setMovie()

        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            navListener?.pop()
        }

        viewModel.movie.observe(this) {
            fillWithInformation(it)
        }
    }

    private fun setUpRecycler() {
        binding?.rvActors?.apply {
            adapter = this@FragmentMoviesDetails.adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setMovie() {
        arguments?.getParcelable<Movie>(KEY_MOVIE)?.let {
            viewModel.setMovie(it)
        }
    }

    private fun fillWithInformation(movie: Movie) {
        Glide.with(requireContext()).load(movie.backdrop).into(binding!!.ivPoster)
       binding?.tvAgeRestriction?.text = getString(R.string.age_format, movie.minimumAge)
       binding?.tvTitle?.text = movie.title
       binding?.tvGenres?.text = movie.genres.joinToString { genre -> genre.name }
       binding?.tvReviews?.text = getString(R.string.reviews_format, movie.numberOfRatings)
       binding?.rbRate?.rating = movie.ratings / 2
       binding?.tvDescription?.text = movie.overview

        if (movie.actors.isEmpty()) binding?.tvCast?.visibility = View.GONE
        else adapter.setActors(movie.actors)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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