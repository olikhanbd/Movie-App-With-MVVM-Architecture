package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.Resource
import com.beeitstudio.movieapp.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Exception

object UserRepository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<Resource<User>> {

        job = Job()

        return object : LiveData<Resource<User>>() {

            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        try {
                            postValue(Resource.loading())
                            val user = ApiClient.apiService.getUser(userId)
                            withContext(Main) {
                                value = Resource.success(user)
                                job.complete()
                            }
                        } catch (e: Exception){
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