package com.beeitstudio.movieapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Movie
import com.beeitstudio.movieapp.utils.AppConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_banner.view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_MOVIE = "movie"

class BannerFragment : Fragment() {

    private var movie: Movie? = null

    private lateinit var rootView: View
    private lateinit var ivBanner: ImageView
    private lateinit var fab: FloatingActionButton
    private lateinit var tvTitle: TextView

    companion object {
        @JvmStatic
        fun newInstance(param1: Movie) =
            BannerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE, param1)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_banner, container, false)
        initView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()
    }

    private fun initView() {
        ivBanner = rootView.iv_banner
        fab = rootView.floatingActionButton
        tvTitle = rootView.tv_title
    }

    private fun setView() {
        movie?.apply {
            tvTitle.text = title
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            val imgurl = "${AppConstants.BASE_IMG_URL}/w185${poster_path}"

            Glide.with(requireContext())
                .load(imgurl)
                .apply(requestOptions)
                .into(ivBanner)
        }
    }
}
