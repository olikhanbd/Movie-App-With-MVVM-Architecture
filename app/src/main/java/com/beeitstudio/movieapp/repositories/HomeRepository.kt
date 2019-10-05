package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.HomeResource
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.models.TmdbResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object HomeRepository {

    var job: CompletableJob? = null

    fun getHomeData(apiKey: String, page: Int, language:String):
            LiveData<Resource<List<HomeResource>>> {

        job = Job()

        return object : LiveData<Resource<List<HomeResource>>>() {

            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        try {
                            postValue(Resource.loading())
                            val nowPlaying =
                                ApiClient.apiService.getNowPlaying(apiKey,page, language)

                            val upcoming =
                                ApiClient.apiService.getUpcoming(apiKey,page, language)

                            val popular =
                                ApiClient.apiService.getPopular(apiKey,page, language)

                            val toprated =
                                ApiClient.apiService.getTopRated(apiKey,page, language)

                            val homeList: MutableList<HomeResource> = ArrayList()

                            homeList.add(HomeResource("Now Playing", nowPlaying))
                            homeList.add(HomeResource("Upcoming", upcoming))
                            homeList.add(HomeResource("Popular", popular))
                            homeList.add(HomeResource("Top Rated", toprated))

                            withContext(Main) {
                                value = Resource.success(homeList)
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