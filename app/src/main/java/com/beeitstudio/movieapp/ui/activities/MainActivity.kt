package com.beeitstudio.movieapp.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.ui.fragments.HomeFragment
import com.beeitstudio.movieapp.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var bottomBar: BottomNavigationView
    lateinit var container: FrameLayout

    private val onNavigationItemReselectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment.newInstance(), "HomeFragment", false)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_browse -> {
                    Toast.makeText(this, "Browse", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.menu_trending -> {
                    Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        bottomBar = findViewById(R.id.design_bottom_sheet)
        container = findViewById(R.id.container)

        bottomBar.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener)
    }

    private fun loadFragment(fragment: Fragment, tag: String, addTobackStack: Boolean){
        val transaction = supportFragmentManager.beginTransaction()

        transaction.let {
            it.replace(container.id, fragment, tag)
            if(addTobackStack)
                it.addToBackStack(tag)
            it.commit()
        }
    }
}
