package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.listeners.MovieSelectionListener
import com.beeitstudio.movieapp.models.HomeResource
import com.beeitstudio.movieapp.models.Movie
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_rv.view.*

class HomeAdapter(private val listener: MovieSelectionListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var parentList: List<HomeResource> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        when (viewType) {
            R.layout.item_banner -> BannerViewHolder(
                listener,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_banner,
                    parent,
                    false
                )
            )
            else -> MovieItemViewHolder(
                listener,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_rv,
                    parent,
                    false
                )
            )
        }


    override fun getItemCount() = parentList.size

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> R.layout.item_banner
            else -> R.layout.item_rv
        }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (parentList.isEmpty())
            return

        when (holder) {
            is BannerViewHolder -> holder.bind(parentList[position].response.results)
            is MovieItemViewHolder -> holder.bind(parentList[position])
        }
    }

    fun submitList(newList: List<HomeResource>) {
        parentList = newList
        notifyDataSetChanged()
    }

    class BannerViewHolder(private val listener: MovieSelectionListener, val view: View) :
        BaseViewHolder<List<Movie>>(view) {

        private val viewpager = view.viewpager
        private val indicator = view.indicator

        override fun bind(item: List<Movie>) {
            val adapter = BannerPagerAdapter(listener, item)
            viewpager.adapter = adapter
            indicator.setupWithViewPager(viewpager)
        }

    }

    class MovieItemViewHolder(
        private val listener: MovieSelectionListener,
        private val view: View
    ) :
        BaseViewHolder<HomeResource>(view) {

        private val rv: RecyclerView = view.rv
        private val tv: TextView = view.tv_title

        override fun bind(item: HomeResource) {

            tv.text = item.type

            rv.layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            rv.addItemDecoration(GridItemDecoration(20))
            val adapter = MovieAdapter(listener)
            adapter.submitList(item.response.results)
            rv.adapter = adapter

        }

    }
}