package com.example.challengeandroid.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiSource.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}