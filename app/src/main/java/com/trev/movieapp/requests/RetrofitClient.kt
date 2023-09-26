package com.trev.movieapp.requests

import com.trev.movieapp.assets.Credentials
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val credentials: Credentials = Credentials()
    private val baseURL = credentials.baseURL

    // Retrofit instance is created only once and reused throughout the app
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}