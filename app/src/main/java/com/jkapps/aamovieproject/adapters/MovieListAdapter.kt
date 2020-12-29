package com.jkapps.aamovieproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.entity.Movie

class MovieListAdapter(private val listener: OnMovieClickListener) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private val movies: MutableList<Movie> = mutableListOf()

    interface OnMovieClickListener {
        fun onClick(movie: Movie)
    }

    fun setMovies(movies: List<Movie>) {
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
        private val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        private val tvGenres: TextView = itemView.findViewById(R.id.tv_genres)
        private val tvAge: TextView = itemView.findViewById(R.id.tv_age_restriction)
        private val rbRate: RatingBar = itemView.findViewById(R.id.rb_rate)
        private val tvReviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val tvDuration: TextView = itemView.findViewById(R.id.tv_duration)

        private val ageFormat: String = itemView.context.getString(R.string.age_format)
        private val reviewsFormat: String = itemView.context.getString(R.string.reviews_format)
        private val durationFormat: String = itemView.context.getString(R.string.duration_format)

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.placeholder)
                .transform(RoundedCorners(20))
                .into(ivPoster)
            tvTitle.text = movie.title
            tvGenres.text = movie.genres.joinToString { genre -> genre.name }
            tvAge.text = String.format(ageFormat, movie.minimumAge)
            rbRate.rating = movie.ratings / 2
            tvReviews.text = String.format(reviewsFormat, movie.numberOfRatings)
            tvDuration.text = String.format(durationFormat, movie.runtime)
            itemView.setOnClickListener {
                listener.onClick(movie)
            }
        }
    }
}