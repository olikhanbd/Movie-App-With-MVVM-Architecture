package com.beeitstudio.movieapp.models

data class Movie (
    val popularity: Double,
    val id: Long,
    val video: Boolean,
    val vote_count: Long,
    val vote_average: Double,
    val title: String,
    val release_date: String,
    val original_language: String,
    val original_title: String,
    val genre_ids: List<Long>,
    val backdrop_path: String? = null,
    val adult: Boolean,
    val overview: String,
    val poster_path: String? = null
)