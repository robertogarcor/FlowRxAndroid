package com.example.rgc.flowrxandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rgc.flowrxandroid.R
import com.example.rgc.flowrxandroid.data.domain.repository.MovieRepositoryImpl
import com.example.rgc.flowrxandroid.data.roomdb.MovieRoomDataSource
import com.example.rgc.flowrxandroid.data.serverdb.MovieApiDataSource
import com.example.rgc.flowrxandroid.ui.adapter.MovieAdapter
import com.example.rgc.flowrxandroid.databinding.ActivityMainBinding
import com.example.rgc.flowrxandroid.ui.custom.app

import com.example.rgc.flowrxandroid.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    private val TAG =  "MainActivity_Items_Scroll"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setTitle(R.string.app_main_name)

        viewModel = getViewModel { buildViewModel() }

        val movieAdapter = MovieAdapter(this@MainActivity)
        binding.recyclerviewMovie.adapter = movieAdapter

        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect { movies -> movieAdapter.submitList(movies)}
        }

        binding.recyclerviewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lm = recyclerView.layoutManager as GridLayoutManager
                val lastVisible = lm.findLastVisibleItemPosition()
                Log.i(TAG, lastVisible.toString())
                viewModel.lastVisibleItem.value = lastVisible

            }
        })
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun buildViewModel() = MainViewModel(
        MovieRepositoryImpl(
            MovieRoomDataSource(app.roomdb),
            MovieApiDataSource(getString(R.string.api_key_remote_repository))
        )
    )

    private inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory : () -> T) : T {
        val vmf = object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = factory() as T
        }
        return ViewModelProvider(this, vmf).get()
    }

}

