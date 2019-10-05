package com.beeitstudio.movieapp.utils

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

class AppUtils {

    companion object {
        fun showSnackBar(activity: Activity, msg: String) {
            val view = activity.window.decorView.findViewById(android.R.id.content) as View
            Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
        }
    }

}