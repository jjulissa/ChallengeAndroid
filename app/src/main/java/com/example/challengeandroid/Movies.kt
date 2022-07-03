package com.example.challengeandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challengeandroid.API.ApiSource
import com.example.challengeandroid.databinding.ItemsMoviesBinding
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get

class Movies :AppCompatActivity() {

    private lateinit var binding: ItemsMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemsMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get().load("${ApiSource.BASE_IMG_URL}${this.intent.extras?.get("poster_path").toString()}").into(
            binding.ivPosterSecond)


//        Picasso.get().load("https://i.pinimg.com/564x/41/c5/09/41c5091c858d4be91bea6bd1fa3e528d.jpg").into(imageView);


        binding.tvGenero.text = this.intent.extras?.get("original_title").toString()
        binding.tvPopularidad.text = this.intent.extras?.get("popularity").toString()
        binding.tvFecha.text = this.intent.extras?.get("release_date").toString()
        binding.idiomaTv.text = this.intent.extras?.get("original_language").toString()


    }
}