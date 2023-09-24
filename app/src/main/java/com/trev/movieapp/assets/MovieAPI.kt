package com.trev.movieapp.assets

import com.trev.movieapp.responses.MovieSearchResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    //'https://api.themoviedb.org/3/search/movie?query=Jack+Reacher&api_key={api_key}'
    //search for movies
    @GET("search/movie")
    fun searchMovie(
        @Query(value = "api_key")api_key: String,
        @Query(value = "query")query: String,
        @Query(value = "page")page: String
    ): Call<MovieSearchResponses>

}