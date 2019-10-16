package com.example.myshopping.utils

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class OkHttpUtils {

    companion object {
        val instance: OkHttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpUtils()
        }
    }

    fun get(url: String): Call {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(url)
            .get()
            .build()
        return client.newCall(request)
    }


}