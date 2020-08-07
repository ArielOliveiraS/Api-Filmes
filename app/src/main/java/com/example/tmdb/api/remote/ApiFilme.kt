package com.example.tmdb.api.remote

import com.example.tmdb.model.MovieResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFilme {
    @GET("movie/popular")
    fun getMovieDetails(@Query("api_key") apiKey: String): Single<MovieResult>
}