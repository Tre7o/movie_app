package com.trev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.trev.movieapp.assets.Credentials
import android.net.Uri
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trev.movieapp.ViewModels.MovieViewModel
import com.trev.movieapp.assets.MovieAPI
import com.trev.movieapp.databinding.ActivityMainBinding
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.requests.Service
import com.trev.movieapp.responses.MovieSearchResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
        println(appLinkData)

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        binding.sendBtn.setOnClickListener {
            getRetrofitResponseUsingID()
//            getRetrofitResponse()
        }
    }

    // observing data changes
    private fun observeDataChanges(){
        movieViewModel.getMovieLiveData().observe(this, Observer {
            
        })
//        movieViewModel.movieLiveData.observe(this, Observer {
//
//        })
    }

    private fun getRetrofitResponse() {
        val credentials: Credentials = Credentials()
        val retrofit = Service().getRetrofitInstance()
        val movieAPI = retrofit.create(MovieAPI::class.java)

        val responseCall: Call<MovieSearchResponses> = movieAPI.searchMovie(
            credentials.api_key, "Action", "1"
        )

        responseCall.enqueue(object : Callback<MovieSearchResponses> {

            override fun onResponse(
                call: Call<MovieSearchResponses>,
                response: Response<MovieSearchResponses>
            ) {
                if(response.isSuccessful){
                    Log.v("MyTag", "Response = ${response.body().toString()}")

//                    val movies: List<MovieModel> = ArrayList(response.body()?.getMovieList() ?: emptyList())

                    val movieList: List<MovieModel> = ArrayList(response.body()!!.getMovieList())

                    for (movieItem in movieList) {
                        Log.v("MyTag", "Title = ${movieItem.original_title}")
                    }

                }else{
                    try {
                        Log.v("MyTag", "Error = ${response.errorBody().toString()}")
                    }catch (exception: Exception){
                        exception.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<MovieSearchResponses>, t: Throwable) {

                Log.v("MyTag", "Error = ${t.localizedMessage}")

            }
        })
    }
    private fun getRetrofitResponseUsingID(){

        val credentials: Credentials = Credentials()
        val retrofit = Service().getRetrofitInstance()
        val movieAPI = retrofit.create(MovieAPI::class.java)

        val responseCall: Call<MovieModel> = movieAPI.getMovie(550, credentials.api_key)

        responseCall.enqueue(object: Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful){
                    val movie: MovieModel? = response.body()
                    Log.v("MyTag", "Title: ${movie!!.original_title}")
                }
                else{
                    try {
                        Log.v("MyTag", "Error = ${response.errorBody().toString()}")
                    }catch (exception: Exception){
                        exception.printStackTrace()
                    }

                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {

            }
        })
    }
}