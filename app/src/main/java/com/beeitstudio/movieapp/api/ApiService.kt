package com.beeitstudio.movieapp.api

import com.beeitstudio.movieapp.models.TmdbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TmdbResponse

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TmdbResponse

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TmdbResponse

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TmdbResponse

}