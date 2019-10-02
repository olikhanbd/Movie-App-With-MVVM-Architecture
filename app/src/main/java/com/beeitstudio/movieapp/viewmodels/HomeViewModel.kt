package com.beeitstudio.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beeitstudio.movieapp.models.DiscoverResponse
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.repositories.MainRepository

class HomeViewModel : ViewModel() {

    val apiKey = "f81089fd8467193bd7777ddd251f2c9e"
    val releaseDate = "2010"
    val sortBy = "vote_average.desc"

    val response: LiveData<Resource<DiscoverResponse>> =
        MainRepository.getDiscovers(apiKey, releaseDate, sortBy)

    fun cancelJob() {
        MainRepository.cancelJobs()
    }
}