package com.example.shoppingmall_kotlin.home.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingmall_kotlin.R
import com.example.shoppingmall_kotlin.home.adapter.HomeAdapter
import com.example.shoppingmall_kotlin.home.bean.BeanHome
import com.example.shoppingmall_kotlin.home.utils.Constants
import com.example.shoppingmall_kotlin.home.utils.OkHttpUtils
import com.google.gson.Gson
import java.util.ArrayList

class HomeFragment : Fragment() {

    lateinit var homeAdapter:HomeAdapter
    var beanHome:BeanHome ?=null
    var handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val s = msg!!.obj as String
            val gson = Gson()
            val beanHome = gson.fromJson(s, BeanHome::class.java)
            activity!!.runOnUiThread(Runnable {
                homeAdapter.bean = beanHome
                homeAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_home,null)
        homeAdapter = HomeAdapter(beanHome,activity)
        var rv_home = view.findViewById<RecyclerView>(R.id.rv_home)
        rv_home.layoutManager = LinearLayoutManager(context)
        rv_home.adapter = homeAdapter
        initData()
        return view
    }

    fun initData(){
        //请求网络
        getDataFromNet()
    }

    fun getDataFromNet(){
        OkHttpUtils.doGet(Constants.HOME_URL,object :OkHttpUtils.MyCallback{
            override fun success(string: String) {
                val message = Message.obtain()
                message.obj = string
                message.what = 1
                handler.handleMessage(message)

            }

            override fun error(error: String) {
                Log.d("rrr",error)
            }

        })
    }


}
