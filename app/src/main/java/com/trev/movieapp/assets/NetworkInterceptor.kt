package com.trev.movieapp.assets

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(context: Context): Interceptor {

    // we need the application context to check whether the internet is available or not
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable()) throw NoInternetException("Make sure you have an active internet connection")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }

}