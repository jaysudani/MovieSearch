package com.example.model

data class MovieList(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)