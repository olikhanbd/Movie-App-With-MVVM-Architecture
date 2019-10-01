package com.beeitstudio.movieapp.models

data class Movie (
    val popularity: Double,
    val id: Long,
    val video: Boolean,
    val voteCount: Long,
    val voteAverage: Long,
    val title: String,
    val releaseDate: String,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIDS: List<Long>,
    val backdropPath: String? = null,
    val adult: Boolean,
    val overview: String,
    val posterPath: String? = null
)