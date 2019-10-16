package com.atguigu.shoppingmall.community.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.community.adapter.HotPostListViewAdapter
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.community.bean.HotPostBean
import com.atguigu.shoppingmall.utils.Constants
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

import okhttp3.Call
import okhttp3.Request

/**
 * Created by Administrator on 2016/10/6.
 */
@SuppressLint("ValidFragment")
class HotPostFragment (var mainActivity: MainActivity) : BaseFragment() {
    private var lv_hot_post: ListView? = null
    private lateinit var result: List<HotPostBean.ResultBean>

    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_hot_post, null)
        lv_hot_post = view.findViewById<View>(R.id.lv_hot_post) as ListView
        return view
    }

    override fun initData() {
        getDataFromNet()
    }

    fun getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOT_POST_URL)
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
                        val adapter = HotPostListViewAdapter(mainActivity, result)
                        lv_hot_post!!.adapter = adapter
                    }
                101 -> Toast.makeText(mainActivity, "https", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun processData(json: String) {
        val gson = Gson()
        val hotPostBean = gson.fromJson<HotPostBean>(json, HotPostBean::class.java)
        result = hotPostBean.result
    }
}
