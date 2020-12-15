package com.jkapps.aamovieproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.jkapps.aamovieproject.R
import com.jkapps.aamovieproject.data.Actor

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ActorViewHolder>() {
    private val actors = mutableListOf<Actor>()

    fun setActors(actors : List<Actor>) {
        this.actors.apply {
            clear()
            addAll(actors)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_item, parent, false)
        return ActorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors[position])
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPhoto : ImageView = itemView.findViewById(R.id.iv_photo)
        private val tvName : TextView = itemView.findViewById(R.id.tv_name)

        fun bind(actor: Actor) {
            Glide.with(itemView)
                .load(actor.picture)
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(20)))
                .into(ivPhoto)

            tvName.text = actor.name
        }
    }

}