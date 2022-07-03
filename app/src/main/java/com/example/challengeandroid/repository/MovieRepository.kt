package com.example.challengeandroid.repository

import com.google.gson.annotations.SerializedName
import org.intellij.lang.annotations.Language

data class MovieRepository (
    @SerializedName("popularity") val popular: Double,
    @SerializedName("original_title") val title: String,
    @SerializedName("original_language") val language: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("id") val id: Int,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("release_date") val release_date: String


)

