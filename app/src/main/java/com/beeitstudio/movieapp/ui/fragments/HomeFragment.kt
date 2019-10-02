package com.beeitstudio.movieapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beeitstudio.movieapp.R
import com.beeitstudio.movieapp.models.Status
import com.beeitstudio.movieapp.ui.adapters.HomeAdapter
import com.beeitstudio.movieapp.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    private lateinit var rootView: View
    private lateinit var rv: RecyclerView
    private lateinit var adapter: HomeAdapter

    private lateinit var viewModel: HomeViewModel

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        initView()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.response.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val data = it.data
                    data?.let { response ->
                        adapter.submitList(response.results)
                    }
                }
                Status.LOADING -> Log.d(TAG, "Loading")
                Status.ERROR -> Log.d(TAG, "Error: ${it.message}")
            }
        })
    }

    private fun initView() {
        rv = rootView.rv

        rv.layoutManager = GridLayoutManager(context, 2)
        adapter = HomeAdapter()
        rv.adapter = adapter
    }

}