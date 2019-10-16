package jni.example.atguigu.utils

import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

object OkUtlis {
    var okHttpClient:OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    interface MyCallback{
        fun success(string: String)
        fun error(error:String)
    }

    fun doGet(url:String,myCallback: MyCallback){
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                myCallback.error(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                myCallback.success(response.body()!!.string())
            }

        })
    }
}