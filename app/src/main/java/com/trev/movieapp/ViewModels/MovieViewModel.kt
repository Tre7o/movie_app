package com.trev.movieapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trev.movieapp.models.MovieModel

// this class is used for the ViewModel
class MovieViewModel: ViewModel() {

    // LiveData
    val movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    fun getMovieLiveData(): LiveData<List<MovieModel>>{
        return movieLiveData
    }

}