package com.trev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.trev.movieapp.assets.Credentials
import android.net.Uri
import android.util.Log
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
        println(appLinkData)

        binding.sendBtn.setOnClickListener {
            getRetrofitResponse()
        }
    }
    private fun getRetrofitResponse() {
        val credentials: Credentials = Credentials()
        val retrofit = Service().getRetrofitInstance()
        val movieAPI = retrofit.create(MovieAPI::class.java)

//        viewLifecycleOwner.lifecycleScope.launch {
//            val response = movieAPI.searchMovie(credentials.api_key, "Jack Reacher", "1")
//            Log.d("MyTag", "Response received: $response")
//        }
//
        val responseCall: Call<MovieSearchResponses> = movieAPI.searchMovie(
            credentials.api_key, "Jack Reacher", "1"
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
                        Log.v("MyTag", "List = ${movieItem.release_date}")
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
                TODO("Not yet implemented")
            }
        })
    }

}