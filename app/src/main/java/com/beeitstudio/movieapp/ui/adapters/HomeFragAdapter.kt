package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.utils.AppConstants.Companion.BASE_IMG_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_home.view.*

class HomeFragAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = HomeFragAdapter::class.java.simpleName

    private var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_home,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is HomeViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(newList: List<Movie>) {
        items = newList
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
        val tvRating = itemView.tv_rating
        val ivPoster = itemView.iv_poster

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvRating.text = movie.vote_average.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imgurl = "$BASE_IMG_URL/w185${movie.poster_path}"

            Glide.with(itemView.context)
                .load(imgurl)
                .apply(requestOptions)
                .into(ivPoster)
        }
    }
}