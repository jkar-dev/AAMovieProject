package com.jkapps.aamovieproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.entity.Movie
import com.jkapps.aamovieproject.databinding.MovieItemBinding

class MovieListAdapter(private val listener: OnMovieClickListener) : ListAdapter<Movie, MovieListAdapter.MovieListViewHolder>(MovieCallback()) {

    interface OnMovieClickListener {
        fun onClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieListViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val ageFormat: String = itemView.context.getString(R.string.age_format)
        private val reviewsFormat: String = itemView.context.getString(R.string.reviews_format)
        private val durationFormat: String = itemView.context.getString(R.string.duration_format)

        fun bind(movie: Movie) {
            Glide.with(itemView)
                .load(movie.poster)
                .placeholder(R.drawable.placeholder)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.ivPoster)
            binding.tvTitle.text = movie.title
            binding.tvGenres.text = movie.genres.joinToString { genre -> genre.name }
            binding.tvAgeRestriction.text = String.format(ageFormat, movie.minimumAge)
            binding.rbRate.rating = movie.ratings / 2
            binding.tvReviews.text = String.format(reviewsFormat, movie.numberOfRatings)
            binding.tvDuration.text = String.format(durationFormat, movie.runtime)
            binding.root.setOnClickListener {
                listener.onClick(movie)
            }
        }
    }

    class MovieCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}