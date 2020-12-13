package com.jkapps.aamovieproject.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.DataSource
import com.jkapps.aamovieproject.model.Movie
import com.jkapps.aamovieproject.ui.MovieListAdapter

class FragmentMoviesList : Fragment(), MovieListAdapter.OnMovieClickListener {
    private var navListener: NavigationListener? = null
    private val adapter = MovieListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        adapter.setMovies(DataSource.movies)
    }

    private fun setUpRecycler() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.rv_movies)
        recycler.apply {
            adapter = this@FragmentMoviesList.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
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

    override fun onClick(movie: Movie) {
        navListener?.openDetails(movie)
    }
}