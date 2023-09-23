package com.trev.movieapp.requests

import com.google.gson.GsonBuilder
import com.trev.movieapp.assets.Credentials
import com.trev.movieapp.assets.MovieAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    private val credentials: Credentials = Credentials()
    private val baseURL = credentials.baseURL

    //
    private var movie_api = getRetrofitInstance().create(MovieAPI::class.java)

    //creating retrofit instance
    fun getRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }
}