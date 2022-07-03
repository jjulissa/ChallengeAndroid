package com.example.challengeandroid.repository

import com.google.gson.annotations.SerializedName

data class MovieResultat (
    @SerializedName("results")
//    val result:List<MovieRepository>,
     val results: List<MovieRepository>

        )
