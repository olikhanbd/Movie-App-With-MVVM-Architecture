package com.beeitstudio.movieapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.ui.adapters.HomeAdapter
import com.beeitstudio.movieapp.viewmodels.HomeViewModel

class HomeActivity : AppCompatActivity() {

    val TAG = HomeActivity::class.java.simpleName

    private lateinit var toolbar: Toolbar
    private lateinit var rv: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

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
                    }
                }
                Status.LOADING -> Log.d(TAG, "Loading")
                Status.ERROR -> Log.d(TAG, "Error: ${it.message}")
            }
        })

    }

    private fun initView() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_movie)

        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL,
            false
        )
        homeAdapter = HomeAdapter()
        rv.adapter = homeAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }
}
