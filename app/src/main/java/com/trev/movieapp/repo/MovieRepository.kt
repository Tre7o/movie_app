package com.trev.movieapp.repo

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trev.movieapp.MainActivity
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.requests.MovieAPIClient

class MovieRepository {

    val mainActivity = MainActivity()
    var movieAPIClient = MovieAPIClient()

    // updating the movieLiveData with the data from MovieAPIClient
    val movieLiveData = movieAPIClient.movieLiveData

    // updating popular movie live data with the data from MovieAPIClient
    val popularMovieLiveData = movieAPIClient.popularMovieLiveData

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

    fun fetchPopularMovies( pageNumber: Int){
//        return movieLiveData
        Log.v("MyTag", "MovieAPIClient instantiated in MovieRepo")
        moviePage = pageNumber

        movieAPIClient.showPopularMovies(moviePage!!)

    }

    fun searchNextPage(){
        if (moviePage == null){
            Toast.makeText(mainActivity.applicationContext, "You've reached the end", Toast.LENGTH_SHORT).show()
        }
        else {
            fetchMovies(movieQuery!!, moviePage!! + 1)
            fetchPopularMovies(moviePage!! + 1)
        }
    }

}