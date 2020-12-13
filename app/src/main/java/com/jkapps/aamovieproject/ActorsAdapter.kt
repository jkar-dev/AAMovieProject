package com.jkapps.aamovieproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jkapps.aamovieproject.model.Actor

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
            ivPhoto.setImageResource(actor.photo)
            tvName.setText(actor.name)
        }
    }

}