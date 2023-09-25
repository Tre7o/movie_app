package com.trev.movieapp.requests

import com.google.gson.GsonBuilder
import com.trev.movieapp.assets.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    private val credentials: Credentials = Credentials()
    private val baseURL = credentials.baseURL

    //creating retrofit instance
    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    //getting retrofit instance
//    private var movie_api = getRetrofitInstance().create(MovieAPI::class.java)
//
//    fun getMovieAPI(): MovieAPI{
//        return movie_api
//    }

}