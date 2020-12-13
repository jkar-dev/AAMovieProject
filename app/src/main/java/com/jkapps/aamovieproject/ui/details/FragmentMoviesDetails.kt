package com.jkapps.aamovieproject.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jkapps.aamovieproject.ActorsAdapter
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.model.Movie

class FragmentMoviesDetails : Fragment() {
    private var navListener : NavigationListener? = null
    private var movie : Movie? = null
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
        setUpRecycler()
        movie?.let { adapter.setActors(it.actors) }

        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            navListener?.pop()
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
        fun instance(movie : Movie) : FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails().apply {
                arguments = Bundle().apply { putParcelable(KEY_MOVIE, movie) }
            }
            return fragment
        }

        private const val KEY_MOVIE = "movie"
    }
}