package com.beeitstudio.movieapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.listeners.MovieSelectionListener
import com.beeitstudio.movieapp.models.Cast
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.utils.AppConstants.Companion.BASE_IMG_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.single_movie_item.view.*

class CastAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = CastAdapter::class.java.simpleName

    private var items: List<Cast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_cast_item,
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
            is CastViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    fun submitList(newList: List<Cast>) {
        items = newList
        notifyDataSetChanged()
    }

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootView = itemView.root_view
        val tvTitle = itemView.tv_title
        val ivPoster = itemView.iv_poster

        fun bind(cast: Cast) {
            tvTitle.text = cast.name

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder)

            val imgurl = "$BASE_IMG_URL/w185${cast.profile_path}"

            Glide.with(itemView.context)
                .load(imgurl)
                .apply(requestOptions)
                .into(ivPoster)
        }
    }
}