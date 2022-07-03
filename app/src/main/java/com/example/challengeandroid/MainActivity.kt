package com.example.challengeandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.challengeandroid.API.Api
import com.example.challengeandroid.API.ApiSource
import com.example.challengeandroid.API.Retrofit
import com.example.challengeandroid.adapter.MovieAdapter
import com.example.challengeandroid.databinding.ActivityMainBinding
import android.annotation.SuppressLint
import com.example.challengeandroid.databinding.ItemsMoviesBinding
import com.example.challengeandroid.repository.MovieRepository
import com.example.challengeandroid.repository.MovieResultat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener,
    MovieAdapter.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    var movies = mutableListOf<MovieRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPopularMovies()
    }

    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.retrofit().create(Api::class.java)
                .fetchPopularMovies("${ApiSource.KEY}")
            val popularMovies = call.body()
            val results = (popularMovies?.results) ?: emptyList()

            updateMovies(movies, results)

            runOnUiThread {
                if (call.isSuccessful && results.isNotEmpty()) {
                    initializeRV(results)
                } else {
                    error()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getMovieByName(name: String?) {
        val name = name ?: ""
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.retrofit().create(Api::class.java).fetchMovieByName(
                name, ApiSource.KEY
            )

            val searchedMovie = call.body()
            val results = (searchedMovie?.results) ?: emptyList()
            updateMovies(movies, results)
            runOnUiThread {
                if (call.isSuccessful && results.isNotEmpty()) {
                    adapter.notifyDataSetChanged()
                    initializeRV(results)
                } else {
                    error()
                }
            }
        }
    }

    fun error() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }


    private fun updateMovies(movies: MutableList<MovieRepository>, newList: List<MovieRepository>) {
        movies.clear()
        movies.addAll(newList)
    }

    private fun initializeRV(movies: List<MovieRepository>) {
        adapter = MovieAdapter(movies, this)
        binding.recyclerMovies.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.searchMovie.setOnQueryTextListener(this)
        binding.recyclerMovies.adapter = adapter

    }

    override fun onQueryTextSubmit(text: String?): Boolean {
        if (!text.isNullOrEmpty()) {
            getMovieByName(text)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean = true

    override fun onItemClick(position: Int) {

        val intent = Intent(this@MainActivity, Movies::class.java)

        intent.putExtra("original_title", movieInPosition(position, movies).title)
        intent.putExtra("original_language", movieInPosition(position, movies).language)
        intent.putExtra("popularity", movieInPosition(position, movies).popular)
        intent.putExtra("release_date", movieInPosition(position, movies).release_date)
        intent.putExtra("poster_path", movieInPosition(position, movies).poster_path)

        startActivity(intent)
    }

    private fun movieInPosition(position: Int, movies: List<MovieRepository>): MovieRepository =
        movies[position]




}