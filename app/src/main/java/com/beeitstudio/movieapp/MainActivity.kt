package com.beeitstudio.movieapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.response.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> Log.d(TAG, it.data.toString())
                Status.LOADING -> Log.d(TAG, "Loading")
                Status.ERROR -> Log.d(TAG, "Error: ${it.message}")
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelJob()
    }
}
