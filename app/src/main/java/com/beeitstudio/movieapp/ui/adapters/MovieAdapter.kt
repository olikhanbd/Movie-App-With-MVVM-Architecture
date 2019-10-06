package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.listeners.MovieSelectionListener
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.utils.AppConstants.Companion.BASE_IMG_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.single_movie_item.view.*

class MovieAdapter(private val listener: MovieSelectionListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = MovieAdapter::class.java.simpleName

    private var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_movie_item,
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
                holder.rootView.setOnClickListener {
                    listener.onMovieSelected(items[position].id, it.iv_poster)
                }
            }
        }
    }

    fun submitList(newList: List<Movie>) {
        items = newList
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootView = itemView.root_view
        val tvTitle = itemView.tv_title
        val tvRating = itemView.tv_rating
        val ivPoster = itemView.iv_poster

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvRating.text = movie.vote_average.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)

            val imgurl = "$BASE_IMG_URL/w185${movie.poster_path}"

            Glide.with(itemView.context)
                .load(imgurl)
                .apply(requestOptions)
                .into(ivPoster)
        }
    }
}