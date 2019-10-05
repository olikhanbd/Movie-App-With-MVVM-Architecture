package com.beeitstudio.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.beeitstudio.movieapp.models.HomeResource
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.models.TmdbResponse
import com.beeitstudio.movieapp.repositories.HomeRepository

class HomeViewModel : ViewModel() {

    val apiKey = "f81089fd8467193bd7777ddd251f2c9e"
    val releaseDate = "2010"
    val sortBy = "vote_average.desc"
    val lan = "en-US"
    val page = 1

    val response: LiveData<Resource<List<HomeResource>>> =
        HomeRepository.getHomeData(apiKey,page, lan)

    fun cancelJob() {
        HomeRepository.cancelJobs()
    }
}