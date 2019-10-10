package com.beeitstudio.movieapp.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.listeners.MovieSelectionListener
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.ui.adapters.HomeAdapter
import com.beeitstudio.movieapp.utils.AppUtils.Companion.showSnackBar
import com.beeitstudio.movieapp.viewmodels.HomeViewModel
import com.facebook.shimmer.ShimmerFrameLayout

class HomeActivity : AppCompatActivity(), MovieSelectionListener {

    private val TAG = HomeActivity::class.java.simpleName

    private lateinit var rootView: ConstraintLayout
    private lateinit var toolbar: Toolbar
    private lateinit var rv: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    private lateinit var shimmerContainer: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initView()

        viewModel.response.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d(TAG, "Success")
                    Log.d(TAG, it.data.toString())
                    val data = it.data
                    data?.let { response ->
                        homeAdapter.submitList(response)
                        hideLoading()
                    }
                }
                Status.LOADING -> {
                    Log.d(TAG, "Loading")
                    showLoading()
                }
                Status.ERROR -> {
                    Log.d(TAG, "Error: ${it.message}")
                    hideLoading()
                    showSnackBar(this, "${it.message}")
                }
            }
        })

    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_movie)

        rootView = findViewById(R.id.root_view)
        shimmerContainer = findViewById(R.id.shimmer_view_container)

        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,
            false
        )
        homeAdapter = HomeAdapter(this)
        rv.adapter = homeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun showLoading() {
        shimmerContainer.visibility = View.VISIBLE
        shimmerContainer.startShimmer()
    }

    private fun hideLoading() {
        shimmerContainer.stopShimmer()
        shimmerContainer.visibility = View.GONE
    }

    override fun onMovieSelected(id: Long, view: View) {
        Log.d(TAG, "onMovieSelected: id: $id")
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.ARG_MOVIE_ID, id)

//        val options = ActivityOptions.makeSceneTransitionAnimation(
//            this, view, "poster"
//        )

        startActivity(intent)
    }

    override fun onBannerItemSelected(id: Long) {
        Log.d(TAG, "onBannerItemSelected: id: $id")
    }
}
