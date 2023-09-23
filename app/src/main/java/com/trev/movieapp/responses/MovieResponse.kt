package com.trev.movieapp.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trev.movieapp.models.MovieModel

//for finding single movie requests

class MovieResponse {
    //1. Finding the movie object
    @SerializedName("results")
    @Expose
    lateinit var movie: MovieModel

    fun getMovie(): MovieModel{
        return movie
    }

    override fun toString(): String {
        return "MovieResponse(movie=$movie)"
    }

}