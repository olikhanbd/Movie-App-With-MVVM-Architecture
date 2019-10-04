package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.utils.AppConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_banner.view.*

class BannerPagerAdapter(private val movies: List<Movie>) :
    PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View?)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.fragment_banner, container, false)

        val ivBanner = view.iv_banner
        val fab = view.floatingActionButton
        val tvTitle = view.tv_title

        val movie = movies[position]

        movie.apply {
            tvTitle.text = title
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imgurl = "${AppConstants.BASE_IMG_URL}/w185${poster_path}"

            Glide.with(view.context)
                .load(imgurl)
                .apply(requestOptions)
                .into(ivBanner)
        }

        container.addView(view)
        return view
    }

    override fun getCount(): Int = movies.size
}