package com.trev.movieapp.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.repo.MovieRepository

// this class is used for the ViewModel
class MovieViewModel: ViewModel() {

    // LiveData
//    val movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    //creating an instance of the MovieRepository in the view model
    val movieRepo: MovieRepository = MovieRepository().getInstance()

    //returning the list of movies from the view model from the movie repo
    fun getMovieLiveData(): LiveData<List<MovieModel>>{
        return movieRepo.getMoviesFromRepo()
    }



}