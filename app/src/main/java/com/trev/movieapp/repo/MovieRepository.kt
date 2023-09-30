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

    var movieQuery: String? = null
    var moviePage: Int? = null


    // getting the list of movies
    fun fetchMovies(query: String, pageNumber: Int){
//        return movieLiveData
        Log.v("MyTag", "MovieAPIClient instantiated in MovieRepo")
        movieQuery = query
        moviePage = pageNumber

        movieAPIClient.startSearch(query, pageNumber)

    }

    fun searchNextPage(){
        fetchMovies(movieQuery!!, moviePage!!+1)
    }

}