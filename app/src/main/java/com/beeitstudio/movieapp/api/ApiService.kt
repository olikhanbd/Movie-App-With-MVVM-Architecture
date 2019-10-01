package com.beeitstudio.movieapp.api

import com.beeitstudio.movieapp.models.DiscoverResponse
import com.beeitstudio.movieapp.models.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("placeholder/user/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    @GET("discover/movie")
    suspend fun getDiscovers(
        @Query("api_key") api_key: String,
        @Query("primary_release_year")
        primary_release_year: String,
        @Query("sort_by") sort_by: String
    ): DiscoverResponse

}