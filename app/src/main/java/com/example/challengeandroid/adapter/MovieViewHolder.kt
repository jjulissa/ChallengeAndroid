package com.example.challengeandroid.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeandroid.API.ApiSource
import com.example.challengeandroid.Movies
import com.example.challengeandroid.databinding.DetailsMoviesBinding
import com.example.challengeandroid.repository.MovieRepository
import com.example.challengeandroid.repository.MovieResultat
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get

class MovieViewHolder(view: View, val listener: MovieAdapter.OnClickListener):RecyclerView.ViewHolder(view) {
        val binding = DetailsMoviesBinding.bind(view)

    fun bind(movie: MovieRepository) {
        Picasso.get().load("${ApiSource.BASE_IMG_URL}${movie.poster_path}").into(binding.ivPoster)
        binding.tvTitle.text = movie.title
}

    init {
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}