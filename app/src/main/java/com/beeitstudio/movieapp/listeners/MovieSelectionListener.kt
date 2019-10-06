package com.beeitstudio.movieapp.listeners

import android.view.View

interface MovieSelectionListener {

    fun onMovieSelected(id: Long, view: View)
    fun onBannerItemSelected(id: Long)
}