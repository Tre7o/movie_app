package com.trev.movieapp.ViewModels

import android.app.DownloadManager.Query
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.repo.MovieRepository
import com.trev.movieapp.requests.MovieAPIClient
import kotlinx.coroutines.launch

// this class is used for the ViewModel
class MovieViewModel: ViewModel() {

    //creating an instance of the MovieRepository in the view model
    val movieRepo = MovieRepository()

    // updating the movieLiveData with the data from MovieRepository
    val movieLiveData: LiveData<List<MovieModel>?> = movieRepo.movieLiveData

    // updating popular movie live data with the data from MovieRepository
    val popularMovieLiveData: LiveData<List<MovieModel>?> = movieRepo.popularMovieLiveData

//    val movieAPIClient = MovieAPIClient()

    fun fetchMovies(query: String, pageNumber: Int){
        movieRepo.fetchMovies(query, pageNumber)
    }

    fun fetchPopularMovies(pageNumber: Int){
        Log.v("MyTag", "Got popular movies data from MovieRepo")
        movieRepo.fetchPopularMovies(pageNumber)
    }

    fun searchNextPageView(){
        movieRepo.searchNextPage()
    }

    //returning the list of movies using the view model from the movie repo
//    fun getMovieLiveData(): LiveData<List<MovieModel>?>{
//        Log.v("MyTag", "Got movie data from MovieRepo")
//        return movieRepo.getMoviesFromRepo()
//    }

}