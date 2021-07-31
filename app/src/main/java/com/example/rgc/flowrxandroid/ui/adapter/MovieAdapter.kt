package com.example.rgc.flowrxandroid.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rgc.flowrxandroid.R
import com.example.rgc.flowrxandroid.ui.custom.AspectRatioImageView
import com.example.rgc.flowrxandroid.data.domain.model.Movie
import com.example.rgc.flowrxandroid.ui.MovieDetailActivity

class MovieAdapter(private val context: Context)
                    : ListAdapter<Movie, MovieAdapter.ItemViewHolder>(MovieDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_movie_vote_progress_horizontal, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(context, movie)
    }


    class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val title_movie : TextView = itemView.findViewById<TextView>(R.id.textView_title_movie)
        private val image_movie : AspectRatioImageView = itemView.findViewById<AspectRatioImageView>(R.id.imageView_movie)
        private val voteAverage_movie : TextView = itemView.findViewById(R.id.textview_voteAverage)
        private val progress_voteAverage_movie : ProgressBar = itemView.findViewById(R.id.progressBar_voteAverage)

        fun bind(context: Context, movie: Movie) {
            title_movie.text = movie.title
            //image_movie.ratio = 1.5f
            val voteAverage: Int = (movie.voteAverage * 10).toInt()
            voteAverage_movie.text = StringBuilder("$voteAverage%")
            progress_voteAverage_movie.progress = movie.voteAverage.toInt()
            Glide.with(context).load("https://image.tmdb.org/t/p/w185/${movie.posterPath}").into(image_movie)
            // Listener item click
            itemView.setOnClickListener(View.OnClickListener {
                //Show detail item movie
                val intent = Intent(context, MovieDetailActivity::class.java).apply {
                    putExtra("MOVIE", movie)
                }
                context.startActivity(intent)
            })
        }
    }

}

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}


