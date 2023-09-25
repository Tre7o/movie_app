package com.trev.movieapp.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.trev.movieapp.models.MovieModel

//this class is responsible for making multiple movie requests to the api
class MovieSearchResponses {
    @SerializedName("total_results")
    @Expose
    private var total_count: Int? = null

    @SerializedName("results")
    @Expose
    private lateinit var movieList: List<MovieModel>

    fun getTotalCount(): Int?{
        return total_count
    }

    //returning multiple movie responses
    fun getMovieList(): List<MovieModel>{
        return movieList
    }

    override fun toString(): String {
        return "MovieSearchResponses(total_count=$total_count, movieList=$movieList)"
    }


}