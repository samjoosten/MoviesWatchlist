package com.example.movieapplication.main

import android.content.Context
import android.view.LayoutInflater.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplication.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import android.view.View
import com.example.movieapplication.R


class MovieAdapter(private val movies: List<Movie>, private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            from(context).inflate(R.layout.item_movie, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }

        @Suppress("DEPRECATION")
        fun bind(movie: Movie) {
            itemView.tvTitle.text = movie.title
            itemView.tvRating.text = movie.rating
            if (movie.notification != null) {
                itemView.ivNotification.setImageDrawable(context.getDrawable(R.drawable.ic_notifications_active_black_24dp))
            } else {
                itemView.ivNotification.setImageDrawable(context.getDrawable(R.drawable.ic_notifications_black_24dp))
            }
        }
    }


}