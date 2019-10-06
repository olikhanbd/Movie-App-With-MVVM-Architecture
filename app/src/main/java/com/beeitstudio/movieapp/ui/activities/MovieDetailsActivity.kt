package com.beeitstudio.movieapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.MovieDetailsResource
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.ui.adapters.CastAdapter
import com.beeitstudio.movieapp.ui.adapters.GridItemDecoration
import com.beeitstudio.movieapp.utils.AppConstants
import com.beeitstudio.movieapp.viewmodels.MovieDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieDetailsActivity : AppCompatActivity() {

    private val TAG = MovieDetailsActivity::class.java.simpleName
    private var movieid = 0L

    private lateinit var ivCover: ImageView
    private lateinit var ivPoster: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvDescription: TextView
    private lateinit var rvCast: RecyclerView
    private lateinit var castAdapter: CastAdapter

    private lateinit var viewModel: MovieDetailsViewModel

    companion object {
        val ARG_MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

        movieid = intent.getLongExtra(ARG_MOVIE_ID, 0)
        Log.d(TAG, "onCreate: id: $movieid")

        initView()

        viewModel.response(movieid).observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d(TAG, "loading")
                }
                Status.ERROR -> {
                    Log.d(TAG, "Error: ${it.message}")
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "Success")
                    setView(it.data)
                }
            }
        })

    }

    private fun initView() {
        ivCover = findViewById(R.id.iv_cover)
        ivPoster = findViewById(R.id.iv_poster)
        tvTitle = findViewById(R.id.tv_title)
        tvRating = findViewById(R.id.tv_rating)
        tvDescription = findViewById(R.id.tv_description)
        rvCast = findViewById(R.id.rv_cast)

        rvCast.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rvCast.addItemDecoration(GridItemDecoration(20))
        castAdapter = CastAdapter()
        rvCast.adapter = castAdapter

    }

    private fun setView(movieDetailsResource: MovieDetailsResource?) {

        val movie = movieDetailsResource?.movieDetails

        movie?.apply {
            tvTitle.text = title
            tvRating.text = vote_average.toString()
            tvDescription.text = overview
        }

        val coverImg = "${AppConstants.BASE_IMG_URL}/w400${movie?.backdrop_path}"
        val poster = "${AppConstants.BASE_IMG_URL}/w185${movie?.poster_path}"

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.poster_placeholder)
            .error(R.drawable.poster_placeholder)

        Glide.with(this)
            .load(coverImg)
            .apply(requestOptions)
            .into(ivCover)

        Glide.with(this)
            .load(poster)
            .apply(requestOptions)
            .into(ivPoster)

        ivCover.animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)

        val cast = movieDetailsResource?.credits?.cast
        if (cast != null)
            castAdapter.submitList(cast)
    }
}
