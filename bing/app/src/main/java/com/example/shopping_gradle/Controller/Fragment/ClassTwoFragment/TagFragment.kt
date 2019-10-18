package com.example.shopping_gradle.Controller.Fragment.ClassTwoFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter.TagAdapter
import com.example.shopping_gradle.Model.Entity.MyTagBean
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.tag_frag.view.*
import okhttp3.Call
import java.lang.Exception

class TagFragment :Fragment(){


    var listResult= mutableListOf<MyTagBean.ResultBean>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.tag_frag, container, false)

        val tag_Recycler = view.Tag_Recycler
        var girdFour:GridLayoutManager= GridLayoutManager(context,3)
        tag_Recycler.layoutManager=girdFour

        initOkUtils(tag_Recycler)


        return view

    }

    private fun initOkUtils(tag_Recycler: RecyclerView) {
        OkHttpUtils.get().url(MyConstants.TAG_URL)
            .id(100)
            .build().execute(object : StringCallback() {
                override fun onError(call: Call?, e: Exception?, id: Int) {
                    Log.e("##","进入失败!")

                }

                override fun onResponse(response: String?, id: Int) {
                    Log.e("##","进入成功!")

                    if(id==100){
                    Log.e("##","进入成功!"+response.toString())
                        val gson = Gson()
                        val fromJson = gson.fromJson(response, MyTagBean::class.java)
                        listResult = fromJson.result as MutableList<MyTagBean.ResultBean>
                        Log.d("Res",listResult.toString())
                        val tagAdapter:TagAdapter= TagAdapter(listResult)
                        tag_Recycler.adapter=tagAdapter

                    }
                }
            })
    }
}