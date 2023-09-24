package com.trev.movieapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface Movie_API {

    @GET("search/movie")
    fun searchMovie(
        @Query(value = "api_key")api_key: String,
        @Query(value = "query")query: String,
        @Query(value = "page")page: String
    ): String

}