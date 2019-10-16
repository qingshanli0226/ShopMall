package com.atguigu.shoppingmall.home.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.alibaba.fastjson.JSON
import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.home.adapter.HomeRecycleAdapter
import com.atguigu.shoppingmall.home.bean.ResultBean
import com.atguigu.shoppingmall.user.activity.MessageCenterActivity
import com.atguigu.shoppingmall.utils.Constants
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

import okhttp3.Request

@SuppressLint("ValidFragment")
class HomeFragment(var mainActivity: MainActivity) : BaseFragment() {


    private lateinit var resultBean: ResultBean.Result
    private var rvHome: RecyclerView? = null
    private var ib_top: ImageView? = null
    private var adapter: HomeRecycleAdapter? = null
    private var tv_search_home: TextView? = null
    private var tv_message_home: TextView? = null


    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_home, null)
        rvHome = view.findViewById(R.id.rv_home)
        ib_top = view.findViewById(R.id.ib_top)
        tv_search_home = view.findViewById(R.id.tv_search_home)
        tv_message_home = view.findViewById(R.id.tv_message_home)
        return view
    }

    override fun initData() {
        //请求网络
        getDataFromNet()

    }

    fun getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(MyStringCallback())
    }

    inner class MyStringCallback : StringCallback() {


        override fun onBefore(request: Request, id: Int) {}

        override fun onAfter(id: Int) {}

        override fun onError(call: okhttp3.Call, e: Exception, id: Int) {

        }

        fun onError(e: Exception) {
            Log.e("TAG", "联网失败" + e.message)
        }

        override fun onResponse(response: String?, id: Int) {

            when (id) {
                100 -> if (response != null) {

                    val gson = Gson()
                    val fromJson = gson.fromJson<ResultBean>(response, ResultBean::class.java)
                    resultBean=fromJson.result
                    //设置RecyclerView
                    adapter = HomeRecycleAdapter(mainActivity, resultBean)
                    rvHome!!.adapter = adapter

                    val manager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                    rvHome!!.layoutManager = manager

                    //滚动监听-->置顶按钮的显示
                    rvHome!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)
                            val findViewByPosition = manager.findViewByPosition(0)
                            if (findViewByPosition != null) {
                                if (findViewByPosition.top<0){
                                    ib_top!!.visibility = View.VISIBLE
                                }else{
                                    ib_top!!.visibility = View.GONE
                                }
                            }
                        }
                    })
                    initListener()
                }
                101 -> {
                }
            }//                    Toast.makeText(mContext, "https", Toast.LENGTH_SHORT).show();
        }

    }
    //设置控件监听
    private fun initListener() {
        //置顶
        ib_top!!.setOnClickListener { rvHome!!.scrollToPosition(0) }

        //搜索
        tv_search_home!!.setOnClickListener { Toast.makeText(mainActivity, "搜索", Toast.LENGTH_SHORT).show() }

        //消息
        tv_message_home!!.setOnClickListener {
            val intent = Intent(mainActivity, MessageCenterActivity::class.java)
            mainActivity.startActivity(intent)
        }

    }

}
