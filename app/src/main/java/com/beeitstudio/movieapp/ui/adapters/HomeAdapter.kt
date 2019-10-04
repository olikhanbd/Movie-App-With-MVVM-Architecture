package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Movie
import kotlinx.android.synthetic.main.item_banner.view.*
import kotlinx.android.synthetic.main.item_rv.view.*

class HomeAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var parentList: List<List<Movie>> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        when (viewType) {
            R.layout.item_banner -> BannerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_banner,
                    parent,
                    false
                )
            )
            else -> MovieItemViewHolder(
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
            is BannerViewHolder -> holder.bind(parentList[0])
            is MovieItemViewHolder -> holder.bind(parentList[1])
        }
    }

    fun submitList(newList: List<List<Movie>>) {
        parentList = newList
        notifyDataSetChanged()
    }

    class BannerViewHolder(val view: View) : BaseViewHolder<List<Movie>>(view) {

        private val viewpager = view.viewpager

        override fun bind(item: List<Movie>) {
            val adapter = BannerPagerAdapter(item)
            viewpager.adapter = adapter
        }

    }

    class MovieItemViewHolder(val view: View) : BaseViewHolder<List<Movie>>(view) {

        private val rv: RecyclerView = view.rv

        override fun bind(item: List<Movie>) {
            rv.layoutManager = GridLayoutManager(view.context, 2)
            val adapter = HomeFragAdapter()
            adapter.submitList(item)
            rv.adapter = adapter

        }

    }
}