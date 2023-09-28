package com.trev.movieapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.requests.MovieAPIClient

class MovieRepository {

    var movieAPIClient = MovieAPIClient()

    // updating the movieLiveData with the data from MovieAPIClient
    val movieLiveData = movieAPIClient.movieLiveData


    // getting the list of movies
    fun fetchMovies(query: String, pageNumber: Int){
//        return movieLiveData
        Log.v("MyTag", "MovieAPIClient instantiated in MovieRepo")
        movieAPIClient.startSearch(query, pageNumber)
    }

}