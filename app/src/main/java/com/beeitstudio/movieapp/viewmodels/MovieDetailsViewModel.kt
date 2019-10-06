package com.beeitstudio.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beeitstudio.movieapp.models.MovieDetails
import com.beeitstudio.movieapp.models.MovieDetailsResource
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.repositories.MovieDetailsRepository

class MovieDetailsViewModel : ViewModel() {

    val apiKey = "f81089fd8467193bd7777ddd251f2c9e"

    fun response(id: Long): LiveData<Resource<MovieDetailsResource>> =
        MovieDetailsRepository.getMovieDetails(apiKey, id)

    fun cancelJobs() {
        MovieDetailsRepository.cancelJobs()
    }
}