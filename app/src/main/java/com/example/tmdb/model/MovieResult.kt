package com.example.tmdb.model

data class MovieResult(
    val page: Int,
    val results: MutableList<Movie>,
    val total_results: Int,
    val total_pages: Int
)