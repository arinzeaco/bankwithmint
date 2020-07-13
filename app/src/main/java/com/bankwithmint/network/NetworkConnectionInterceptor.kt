package com.bankwithmint.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.bankwithmint.constant.Constant
import com.bankwithmint.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class NetworkConnectionInterceptor(applicationContext: Context) : Interceptor {
    private val mContext: Context=applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw NoInternetException("Make sure you have an active data connection")            // Throwing our custom exception 'NoConnectivityException'
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())    }

    fun connectedToTheNetwork(): Boolean {
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    fun isNetworkAvailable(): Boolean {

        if (connectedToTheNetwork()) {
            Thread(Runnable {

                try {
                    val urlc = URL(Constant.BASE_URL)
                        .openConnection() as HttpURLConnection
                    urlc.setRequestProperty("User-Agent", "Android")
                    urlc.setRequestProperty("Connection", "close")
                    urlc.connectTimeout = 1500
                    urlc.connect()

                } catch (e: IOException) {
                    Log.e("BaseActivity__", "Error checking internet connection", e)
                }
            }).start()
            return true

        } else {
            Log.d("BaseActivity__" ,"No network available!")
        }
        return false
    }



}