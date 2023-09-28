package com.trev.movieapp.assets

import com.trev.movieapp.models.MovieModel
import com.trev.movieapp.responses.MovieSearchResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    //'https://api.themoviedb.org/3/search/movie?query=Jack+Reacher&api_key={api_key}'
    //search for movies
    @GET("search/movie")
    fun searchMovie(
        @Query(value = "api_key")api_key: String,
        @Query(value = "query")query: String,
        @Query(value = "page")page: Int
    ): Call<MovieSearchResponses>

    // https://api.themoviedb.org/3/movie/550?api_key=be629fc225dc4ddfb916e04cb6e7625e
    // search for a specific movie
    // movie_id = 550 id for Fight Club
    @GET("movie/{movie_id}?")
    fun getMovieUsingID(
        @Path("movie_id")movie_id: Int,
        @Query(value = "api_key")api_key: String,
    ): Call<MovieModel>

}