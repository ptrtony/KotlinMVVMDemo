package com.xilaida.mvvmlibrary.data.api

import android.content.Context
import android.net.ConnectivityManager
import com.xilaida.mvvmlibrary.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
@author cjq
@Date 6/9/2020
@Time 3:12 PM
@Describe:
 */
class NetworkConnectionInterceptor (
    context: Context
):Interceptor{
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
           throw NoInternetException("确保你的网络连接正常")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable():Boolean{
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it!= null && it.isConnected

        }
    }

}