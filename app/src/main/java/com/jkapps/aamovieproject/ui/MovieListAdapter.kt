package com.jkapps.aamovieproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.model.Movie

class MovieListAdapter(private val listener : OnMovieClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private val movies : MutableList<Movie> = mutableListOf()

    interface OnMovieClickListener {
        fun onClick(movie : Movie)
    }

    fun setMovies(movies : List<Movie>) {
        this.movies.apply {
            clear()
            addAll(movies)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster : ImageView = itemView.findViewById(R.id.iv_poster)
        private val tvTitle : TextView = itemView.findViewById(R.id.tv_title)
        private val tvGenres : TextView = itemView.findViewById(R.id.tv_genres)
        private val tvAge : TextView = itemView.findViewById(R.id.tv_age_restriction)
        private val rbRate : RatingBar = itemView.findViewById(R.id.rb_rate)
        private val tvReviews : TextView = itemView.findViewById(R.id.tv_reviews)
        private val tvDuration : TextView = itemView.findViewById(R.id.tv_duration)

        fun bind(movie : Movie) {
            ivPoster.setImageResource(movie.image)
            tvTitle.text = movie.title
            tvGenres.text = movie.genres.joinToString()
            tvAge.text = movie.ageRestriction
            rbRate.rating = movie.rate
            tvReviews.text = movie.numberOfReviewers
            tvDuration.text = movie.duration
            itemView.setOnClickListener {
                listener.onClick(movie)
            }
        }
    }
}