package com.trev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.trev.movieapp.databinding.ActivityMovieDetailsBinding
import com.trev.movieapp.models.MovieModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromIntent()

    }

    private fun getDataFromIntent(){
        val bundle = intent.extras
        binding.movieDetails.text = bundle!!.getString("title")

        Glide.with(this)
            .load(bundle.getString("image"))
            .into(binding.movieImage)

        binding.overview.text = bundle.getString("overview")
        binding.ratingBar.rating = bundle.getFloat("movie_rating")

        Log.v("MyTag", "Intent: ${binding.ratingBar.rating}")

//        if (intent.hasExtra("movie")){
//            val model: MovieModel? = bundle.getParcelable<MovieModel>("movie")
//            Log.v("MyTag", "Intent: ${model?.id}")
//        }
    }
}