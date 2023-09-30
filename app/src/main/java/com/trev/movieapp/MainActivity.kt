package com.trev.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import com.trev.movieapp.assets.Credentials
import android.net.Uri
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.trev.movieapp.ViewModels.MovieViewModel
import com.trev.movieapp.adapters.MovieAdapter
import com.trev.movieapp.requests.RetrofitClient
import com.trev.movieapp.assets.MovieAPI
import com.trev.movieapp.databinding.ActivityMainBinding
import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.responses.MovieSearchResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieRecycler: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private var isPopular = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coordinatorLayout = binding.coordinator

        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent: Intent = intent
        val appLinkAction: String? = appLinkIntent.action
        val appLinkData: Uri? = appLinkIntent.data
        println(appLinkData)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        movieRecycler = binding.movieRecycler
        movieAdapter = MovieAdapter()

        movieRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movieRecycler.adapter = movieAdapter

        // RecyclerView Pagination
        // allow user to go to next page of query results
        movieRecycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(recyclerView.canScrollHorizontally(1)){
//                    val snackbar: Snackbar = Snackbar.make(coordinatorLayout, "You've reached the end", Snackbar.LENGTH_SHORT)
//                    snackbar.setAction("CLOSE", View.OnClickListener {
//
//                    })
//                    snackbar.setActionTextColor(Color.CYAN)
//                    snackbar.show()
                }else{
                    // if recyclerView can't scroll horizontally anymore
                    // display next results
                    movieViewModel.searchNextPageView()
                }
            }
        })

        // allow user to search for movies
        setUpSearchView()
        observeDataChanges()

        // observe popular movie changes
        getPopularMovies(1)
        observePopularMovies()

    }

    private fun observePopularMovies() {
        movieViewModel.popularMovieLiveData.observe(this, Observer<List<MovieModel>?> { popularMovies ->
            // This block will be executed when popularMovieLiveData changes
            movieAdapter.submitList(popularMovies)
            if (popularMovies != null){
                for (popMovie in popularMovies){
                    Log.v("MyTag", "Observer: ${popMovie.original_title}")
                }
            }
        })
    }

    // observing data changes
    private fun observeDataChanges(){

        movieViewModel.movieLiveData.observe(this, Observer<List<MovieModel>?> { movies ->
            // This block will be executed when movieLiveData changes
            movieAdapter.submitList(movies)
            if (movies != null){
                for (movie in movies){
                    Log.v("MyTag", "Observer: ${movie.original_title}")
                }
            }
        })
    }

    // query for searching movies
    private fun searchMovieApi(query: String, pageNumber: Int){
        movieViewModel.fetchMovies(query, pageNumber)
    }

    private fun getPopularMovies(pageNumber: Int){
        Log.v("MyTag", "Observer: getPopularMovies() launched")
        movieViewModel.fetchPopularMovies(1)
    }

    // get data from search view and make call to the API
    private fun setUpSearchView(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovieApi(query!!, pageNumber = 1)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.searchView.setOnSearchClickListener {
            isPopular = false
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.search_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }


    private fun getRetrofitResponse() {
        val credentials = Credentials()
        val retrofit = RetrofitClient.instance
        val movieAPI = retrofit.create(MovieAPI::class.java)

        val responseCall: Call<MovieSearchResponses> = movieAPI.searchMovie(
            credentials.api_key, "Action", 1
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

        val credentials = Credentials()
        val retrofit = RetrofitClient.instance
        val movieAPI = retrofit.create(MovieAPI::class.java)

        val responseCall: Call<MovieModel> = movieAPI.getMovieUsingID(550, credentials.api_key)

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

