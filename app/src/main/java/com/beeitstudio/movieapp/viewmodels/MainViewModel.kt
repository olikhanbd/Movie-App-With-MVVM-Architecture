package com.beeitstudio.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.beeitstudio.movieapp.models.User
import com.beeitstudio.movieapp.repositories.UserRepository

class MainViewModel : ViewModel() {

    private val _userId: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations
        .switchMap(_userId) { userid ->
            UserRepository.getUser(userid)
        }

    fun setUserid(userid: String) {
        val update = userid
        if (_userId.value == update)
            return
        _userId.value = update
    }

    fun cancelJob() {
        UserRepository.cancelJobs()
    }
}