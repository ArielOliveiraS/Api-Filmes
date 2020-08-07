package com.example.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.model.Movie
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.movie_item_recyclerview.view.*

class MovieRecyclerViewAdapter(var movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_recyclerview, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.onBind(movie)

    }

    fun updateList(newList: MutableList<Movie>) {
        this.movieList.removeAll(movieList)
        this.movieList = newList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(movie: Movie) {
            itemView.movieName.text = movie.title
            //itemView.teste.text = movie.title
           // Picasso.get().load(POSTER_BASE_URL + movie.posterPath).into(itemView.movieImage)
        }
    }
}