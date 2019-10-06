package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.MovieDetails
import com.beeitstudio.movieapp.models.MovieDetailsResource
import com.beeitstudio.movieapp.models.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MovieDetailsRepository {

    var job: CompletableJob? = null

    fun getMovieDetails(apiKey: String, id: Long): LiveData<Resource<MovieDetailsResource>> {

        job = Job()

        return object : LiveData<Resource<MovieDetailsResource>>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {

                        postValue(Resource.loading())
                        try {
                            val movieDetails = ApiClient.apiService.getMovieDetails(id, apiKey)
                            val creditResponse = ApiClient.apiService.getCredits(id, apiKey)

                            val movieDetailsResource =
                                MovieDetailsResource(movieDetails, creditResponse)

                            withContext(Main) {
                                value = Resource.success(movieDetailsResource)
                                it.complete()
                            }

                        } catch (e: Exception) {
                            postValue(Resource.error(e.message))
                        }
                    }
                }
            }
        }

    }

    fun cancelJobs() {
        job?.cancel()
    }
}