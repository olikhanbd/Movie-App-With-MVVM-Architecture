package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.MovieDetails
import com.beeitstudio.movieapp.models.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MovieDetailsRepository {

    var job: CompletableJob? = null

    fun getMovieDetails(apiKey: String, id: Long): LiveData<Resource<MovieDetails>> {

        job = Job()

        return object : LiveData<Resource<MovieDetails>>() {
            override fun onActive() {
                super.onActive()
                job?.let {
                    CoroutineScope(IO + it).launch {

                        postValue(Resource.loading())
                        try {
                            val movieDetails = ApiClient.apiService.getMovieDetails(id, apiKey)

                            withContext(Main) {
                                value = Resource.success(movieDetails)
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