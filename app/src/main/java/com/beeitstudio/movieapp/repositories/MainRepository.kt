package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.DiscoverResponse
import com.beeitstudio.movieapp.models.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {

    var job: CompletableJob? = null

    fun getDiscovers(apiKey: String, releaseDate: String, sortBy: String):
            LiveData<Resource<DiscoverResponse>> {

        job = Job()

        return object : LiveData<Resource<DiscoverResponse>>() {

            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        try {
                            postValue(Resource.loading())
                            val discovers =
                                ApiClient.apiService.getDiscovers(apiKey, releaseDate, sortBy)
                            withContext(Main) {
                                value = Resource.success(discovers)
                                job.complete()
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