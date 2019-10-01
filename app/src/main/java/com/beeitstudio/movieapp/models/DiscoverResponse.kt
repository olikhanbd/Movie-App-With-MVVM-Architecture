package com.beeitstudio.movieapp.models

data class DiscoverResponse (
    val page: Long,
    val totalResults: Long,
    val totalPages: Long,
    val results: List<Movie>
)