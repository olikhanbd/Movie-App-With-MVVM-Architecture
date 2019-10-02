package com.beeitstudio.movieapp.models

data class TmdbResponse (
    val page: Long,
    val totalResults: Long,
    val totalPages: Long,
    val results: List<Movie>
)