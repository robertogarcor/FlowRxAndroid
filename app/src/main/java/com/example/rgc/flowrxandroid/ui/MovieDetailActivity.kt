package com.example.rgc.flowrxandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.rgc.flowrxandroid.databinding.ActivityMovieDetailBinding
import com.example.rgc.flowrxandroid.data.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getSerializableExtra("MOVIE") as Movie
        setTitle(movie.title)

        with(binding) {
            textViewTitleDetailMovie.text = movie.title
            ratingBarVoteAverageMovie.rating = movie.voteAverage.toFloat() / 2
            textViewVoteAverageDetailMovie.text = StringBuilder("${movie.voteAverage} / 10")
            textViewOverviewDetailMovie.text = movie.overview
            Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
                .into(imageViewDetailMovie);
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }

}