package com.trev.movieapp.requests

import android.app.DownloadManager.Query
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trev.movieapp.assets.Credentials
import com.trev.movieapp.assets.MovieAPI
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.responses.MovieSearchResponses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieAPIClient() {

    //creating an instance of movieLiveData
    var movieLiveData: MutableLiveData<List<MovieModel>?> = MutableLiveData()

    private var apiRequestJob: Job? = null
    private var scope = CoroutineScope(Dispatchers.Default)

//    private var query: String? = null
//    private var pageNumber: Int? = null

//    fun getClientInstance(): LiveData<List<MovieModel>?>{
////        delay(4000)
//        startSearchWithCancellation()
//        Log.v("MyTag", "MovieAPIClient instance created")
//        return movieLiveData
//    }

    fun startSearch(query: String, pageNumber: Int) {

        apiRequestJob = scope.launch {
            val result = withTimeoutOrNull(5000) { // wait 5 seconds
                searchMoviesAPI(query, pageNumber) // function to carry out API request
            }

            if (result != null) {
                Log.v("MyTag", "Search succeeded")
                val response = searchMoviesAPI(query, pageNumber).execute()
                if (response.isSuccessful){
                    val list: List<MovieModel> = ArrayList(response.body()!!.getMovieList()) // list to store the movie response
                    if (pageNumber == 1) {
                        movieLiveData.postValue(list)
                        Log.v("MyTag", "Movie 1: ${movieLiveData.value}")
                    }else{
                        movieLiveData.postValue(list)
                        Log.v("MyTag", "Movie 2: ${movieLiveData.value}")
                    }
                }else{
                    Log.v("MyTag", "Error: ${response.errorBody().toString()}")
                    movieLiveData.postValue(null)
                }
            } else {
                cancelSearch() // if there's no request cancel search
            }
        }
    }


    // Function to cancel the API request
    fun cancelSearch() {
        Log.v("MyTag", "Cancelling request")
        apiRequestJob?.cancel()
    }

    fun searchMoviesAPI(query: String, pageNumber: Int): Call<MovieSearchResponses>{
        val credentials = Credentials()
        val retrofit = RetrofitClient.instance
        val movieAPI = retrofit.create(MovieAPI::class.java)

        return movieAPI.searchMovie(credentials.api_key, query, pageNumber)

    }

}

//class RetrieveMovies {
//    private var query: String? = null
//    private var pageNumber: Int? = null
//}