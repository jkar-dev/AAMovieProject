package com.jkapps.aamovieproject.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jkapps.aamovieproject.App
import com.jkapps.aamovieproject.MyViewModelFactory
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.adapters.MovieListAdapter

class FragmentMoviesList : Fragment(), MovieListAdapter.OnMovieClickListener {
    private var navListener: NavigationListener? = null
    private val adapter = MovieListAdapter(this)
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        initViewModel()

        viewModel.movies.observe(this) {
            adapter.setMovies(it)
        }

    }

    private fun setUpRecycler() {
        val recycler = requireView().findViewById<RecyclerView>(R.id.rv_movies)
        recycler.apply {
            adapter = this@FragmentMoviesList.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun initViewModel() {
        val factory = MyViewModelFactory(App.instance!!.interactor)
        viewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]
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