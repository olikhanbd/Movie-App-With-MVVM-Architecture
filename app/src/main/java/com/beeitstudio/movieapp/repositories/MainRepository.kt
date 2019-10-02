package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.models.TmdbResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object MainRepository {

    var job: CompletableJob? = null

    fun getLatest(apiKey: String, page: Int, language:String):
            LiveData<Resource<TmdbResponse>> {

        job = Job()

        return object : LiveData<Resource<TmdbResponse>>() {

            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        try {
                            postValue(Resource.loading())
                            val discovers =
                                ApiClient.apiService.getLatest(apiKey, page, language)
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