package com.trev.movieapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trev.movieapp.models.MovieModel

class MovieRepository {

    //creating an instance of movieRepository
    var movieRepository: MovieRepository? = null

    //creating an instance of the movie LiveData
    val movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    //fetching the movieRepository instance
    fun getInstance(): MovieRepository{
        if(movieRepository == null){
            movieRepository = MovieRepository()
        }
        return movieRepository as MovieRepository
    }

    //getting the list of movies
    fun getMoviesFromRepo(): LiveData<List<MovieModel>>{
        return movieLiveData
    }

}