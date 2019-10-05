package com.beeitstudio.movieapp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.beeitstudio.movieapp.R

class MovieDetailsActivity : AppCompatActivity() {

    private val TAG = MovieDetailsActivity::class.java.simpleName
    private var movieid = 0L

    companion object {
        val ARG_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        movieid = intent.getLongExtra(ARG_MOVIE_ID, 0)
        Log.d(TAG, "onCreate: id: $movieid")

    }
}
