package com.jkapps.aamovieproject.ui.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jkapps.aamovieproject.App
import com.jkapps.aamovieproject.MyViewModelFactory
import com.jkapps.aamovieproject.NavigationListener
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.adapter.MovieListAdapter
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.databinding.FragmentMoviesListBinding

class FragmentMoviesList : Fragment(), MovieListAdapter.OnMovieClickListener {
    private lateinit var viewModel: MovieListViewModel
    private val adapter = MovieListAdapter(this)
    private var binding: FragmentMoviesListBinding? = null
    private var navListener: NavigationListener? = null
    private var snackbar : Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        initViewModel()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.movies.observe(this) {
            adapter.submitList(it)
        }
        viewModel.isLoading.observe(this) {
            setLoading(it)
        }
        viewModel.error.observe(this) {
            it.getContentIfNotHandled()?.let {
                showError()
            }
        }
    }

    private fun showError() {
        if (snackbar == null) snackbar =
            Snackbar.make(requireView(), R.string.error_message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again) {
                    viewModel.loadMovies()
                }

        snackbar?.show()
    }

    private fun setLoading(isLoading: Boolean) {
        binding?.pbLoading?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpRecycler() {
        binding?.rvMovies?.apply {
            adapter = this@FragmentMoviesList.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(rv, newState)

                    val lastVisiblePosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    if (lastVisiblePosition > adapter!!.itemCount - 2) {
                        viewModel.loadMovies()
                    }
                }
            })
        }
    }

    private fun initViewModel() {
        val factory = MyViewModelFactory(App.instance!!.repository)
        viewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

        snackbar?.dismiss()
        snackbar = null
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