package com.example.challengeandroid.API

import com.example.challengeandroid.repository.MovieRepository
import com.example.challengeandroid.repository.MovieResultat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular?")
    suspend fun fetchPopularMovies(
        @Query("api_key") api_key: String
    ): Response<MovieResultat>

    @GET("search/movie?")
    suspend fun fetchMovieByName(
        @Query("query") query: String,
        @Query("api_key") api_key: String
    ): Response<MovieResultat>


}