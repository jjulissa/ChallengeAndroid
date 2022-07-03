package com.example.challengeandroid.adapter

import android.view.LayoutInflater
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import com.example.challengeandroid.R
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeandroid.MainActivity
import com.example.challengeandroid.Movies
import com.example.challengeandroid.databinding.DetailsMoviesBinding
import com.example.challengeandroid.databinding.ItemsMoviesBinding
import com.example.challengeandroid.repository.MovieRepository
import com.example.challengeandroid.repository.MovieResultat


class MovieAdapter(
    private val movies: List<MovieRepository>,
    private val listener: MainActivity
) : RecyclerView.Adapter<MovieViewHolder>() {

    interface OnClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(
            layoutInflater.inflate(R.layout.details_movies, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem: MovieRepository = movies[position]
        holder.bind(movieItem)
    }


    override fun getItemCount(): Int = movies.size


}