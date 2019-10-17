package com.example.shopping_gradle.Controller.Fragment.TypeFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import okhttp3.Call
import java.lang.Exception

class TagFragment :Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tag_frag, container, false)

        initOkUtils()

        return view

    }

    private fun initOkUtils() {
        OkHttpUtils.get().url(MyConstants.TAG_URL)
            .id(100)
            .build().execute(object : StringCallback() {
                override fun onError(call: Call?, e: Exception?, id: Int) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(response: String?, id: Int) {
                    if(id==100){

                    }
                }
            })
    }
}