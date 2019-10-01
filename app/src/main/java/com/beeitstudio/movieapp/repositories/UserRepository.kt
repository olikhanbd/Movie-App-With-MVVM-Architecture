package com.beeitstudio.movieapp.repositories

import androidx.lifecycle.LiveData
import com.beeitstudio.movieapp.api.ApiClient
import com.beeitstudio.movieapp.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object UserRepository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User> {

        job = Job()

        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { job ->
                    CoroutineScope(IO + job).launch {
                        val user = ApiClient.apiService.getUser(userId)
                        withContext(Main) {
                            value = user
                            job.complete()
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