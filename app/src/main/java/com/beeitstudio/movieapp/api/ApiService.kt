package com.beeitstudio.movieapp.api

import com.beeitstudio.movieapp.models.TmdbResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getLatest(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
        @Query("language") language: String
    ): TmdbResponse

}