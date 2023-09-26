package com.trev.movieapp.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trev.movieapp.models.MovieModel

class MovieAPIClient {

    //creating an instance of the movie LiveData
    val movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun getClientInstance(): LiveData<List<MovieModel>>{
        return movieLiveData
    }

}