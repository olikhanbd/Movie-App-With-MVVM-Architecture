package com.beeitstudio.movieapp.models

data class MovieDetails(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any? = null,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdb_iD: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
)

data class Genre(
    val id: Long,
    val name: String
)

data class ProductionCompany(
    val id: Long,
    val logo_path: String? = null,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val iso639_1: String,
    val name: String
)
