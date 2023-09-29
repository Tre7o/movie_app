package com.trev.movieapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trev.movieapp.R
import com.trev.movieapp.models.MovieModel


class MovieAdapter : ListAdapter<MovieModel, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movieModel: MovieModel) {
            // getting all the card items

            //using glide library for the image
            val imageView = itemView.findViewById<ImageView>(R.id.imagePoster)
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/"+movieModel.poster_path)
                .into(imageView)

            val titleView = itemView.findViewById<TextView>(R.id.movie_title)
            titleView.setText(movieModel.original_title)

            val releaseDateView = itemView.findViewById<TextView>(R.id.release_date)
            releaseDateView.setText(movieModel.release_date)

            val durationView = itemView.findViewById<TextView>(R.id.movie_duration)
            if (movieModel.runtime != null){
                durationView.setText(movieModel.runtime!!)
            }else{
                durationView.text = "N/A"
            }


            val ratingBarView = itemView.findViewById<RatingBar>(R.id.rating_bar)
            ratingBarView.rating = movieModel.vote_average.toFloat()

            // when the movie card is clicked
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "You selected ${movieModel.original_title } item", Toast.LENGTH_SHORT).show()
            }

        }

    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}
