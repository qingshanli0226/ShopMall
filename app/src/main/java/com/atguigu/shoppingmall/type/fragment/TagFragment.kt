package com.atguigu.shoppingmall.type.fragment


import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.type.adapter.TagGridViewAdapter
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.type.bean.TagBean
import com.atguigu.shoppingmall.utils.Constants
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

import okhttp3.Call
import okhttp3.Request

/**
 * A simple [Fragment] subclass.
 */
@SuppressLint("ValidFragment")
class TagFragment(var mainActivity: MainActivity) : BaseFragment() {

    private var gv_tag: GridView? = null
    private var adapter: TagGridViewAdapter? = null
    private lateinit var result: List<TagBean.ResultBean>

    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_tag, null)
        gv_tag = view.findViewById<View>(R.id.gv_tag) as GridView

        return view
    }

    override fun initData() {
        getDataFromNet()

    }


    fun getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.TAG_URL)
                .id(100)
                .build()
                .execute(MyStringCallback())
    }

    inner class MyStringCallback : StringCallback() {


        override fun onBefore(request: Request, id: Int) {}

        override fun onAfter(id: Int) {}

        override fun onError(call: Call, e: Exception, id: Int) {
            Log.e("TAG", "联网失败" + e.message)
        }

        override fun onResponse(response: String?, id: Int) {

            when (id) {
                100 ->
                    //                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response)
                        adapter = TagGridViewAdapter(mainActivity, result)
                        gv_tag!!.adapter = adapter
                    }
                101 -> Toast.makeText(mainActivity, "https", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun processData(json: String) {
        val gson = Gson()
        val tagBean = gson.fromJson<TagBean>(json, TagBean::class.java)
        result = tagBean.result
    }

}
