package com.example.shoppingapplication.home.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingapplication.R
import okhttp3.*
import java.io.IOException

class HomeFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = View.inflate(context,R.layout.fragment_home,null)

        initData()

        return v
    }

    private fun initData() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().get().url("").build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                
            }
            override fun onResponse(call: Call, response: Response) {

            }

        })
    }

}