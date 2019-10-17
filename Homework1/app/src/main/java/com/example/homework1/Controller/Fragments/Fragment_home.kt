package com.example.homework1.Controller.Fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.Controller.Adapter.MyAdapter
import com.example.homework1.Controller.Adapter.ViewHolder
import com.example.homework1.R
import com.example.homework1.Utils.Constants
import com.example.homework1.Utils.HttpThread
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Fragment_home : BaseFragment() {

    lateinit var view_recycler : RecyclerView
    lateinit var img_topbtn : ImageView
    lateinit var tv_search_home : TextView
    lateinit var tv_message_home : TextView
    lateinit var handler: Handler
    lateinit var myAdapter1 : MyAdapter
    lateinit var manager : LinearLayoutManager

    var datas : ArrayList<Map<String,Object>> = arrayListOf()
    override fun initView(): View {
        val view = View.inflate(mContext, R.layout.fragment_home, null)

        view_recycler = view.findViewById(R.id.view_recycler)
        img_topbtn = view.findViewById(R.id.img_topbtn)
        tv_search_home = view.findViewById(R.id.tv_search_home)
        tv_message_home = view.findViewById(R.id.tv_message_home)

        initRecycler()
        initHandler()
        initListener()
        return view
    }

    private fun initRecycler() {
        manager = LinearLayoutManager(context)
        manager.orientation = RecyclerView.VERTICAL
        view_recycler.layoutManager = manager
        myAdapter1 = object : MyAdapter(){
            override fun bind(holder: ViewHolder, position: Int) {
                val type = datas[position].get("type").toString()
                println("$type")
                when(type){
                    "0" -> setBanner(holder,position)
                    "1" -> setchannelinfo(holder,position)
                }
            }
        }
        view_recycler.adapter = myAdapter1
    }


    private fun initHandler() {
        handler = @SuppressLint("HandlerLeak")
        object : Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){
                    100 -> getJson(msg)
                }
            }
        }
    }

    private fun getJson(msg:Message){
        var  toString : String = msg.obj.toString()
        try {
            val jsonObject = JSONObject(toString)
            val jsonObject1 = jsonObject.getJSONObject("result")
            initBanner(jsonObject1)
            initchannelinfo(jsonObject1)

        }catch (e : JSONException){

        }

    }

    private fun initchannelinfo(jsonObject: JSONObject) {
        val jsonArray = jsonObject.getJSONArray("channel_info")
        var dataz : ArrayList<Map<String,Object>> = arrayListOf()
        for (i in 0 until jsonArray.length()){
            var hashMap : HashMap<String,Object> = hashMapOf()
            val jsonObject2 = jsonArray.getJSONObject(i)
            var string :String = "${Constants.BASE_URl_IMAGE}${jsonObject2.getString("image")}"
            println(string)

            hashMap.put("type","2" as Object)
            hashMap.put("channel_name",jsonObject2.getString("channel_name") as Object)
            hashMap.put("image", string as Object)

            dataz.add(hashMap)
        }
        var hashMap : HashMap<String,Object> = hashMapOf()

        hashMap.put("type","1" as Object)
        hashMap.put("data",dataz as Object)

        datas.add(hashMap)

        println("size: ${datas.size}")
        Activity().runOnUiThread(object : Runnable{
            override fun run() {
                for (i in datas){
                    println(i.toString())
                }
                myAdapter1.refresh(datas)
            }
        })
    }

    private fun setchannelinfo(holder: ViewHolder, position: Int) {

        println("进入channel")
        val map = datas[position]

        var context : Context? = context
        if(context!=null){
            holder.setRecycler(R.id.rv_recycler,map.get("data") as ArrayList<Map<String, Object>>,getContext())
        }

    }

    private fun initBanner(jsonObject:JSONObject) {
        val jsonArray = jsonObject.getJSONArray("banner_info")

        var images : ArrayList<String> = arrayListOf()
        for (i in 0 until jsonArray.length()){
            val jsonObject2 = jsonArray.getJSONObject(i)

            val string = jsonObject2.getString("image")
            images.add("${Constants.BASE_URl_IMAGE}$string")
        }
        var hashMap : HashMap<String,Object> = hashMapOf()
        hashMap.put("data",images as Object)
        hashMap.put("type","0" as Object)
        datas.add(hashMap)
        println("Banner加入成功")
    }

    private fun setBanner(holder: ViewHolder, position: Int) {
        val map = datas[position]
        holder.setBanner(R.id.mybanner,map.get("data") as ArrayList<String>)
    }

    private fun initListener() {
        img_topbtn.setOnClickListener {
            view_recycler.scrollToPosition(0)
        }

        tv_search_home.setOnClickListener {
            Toast.makeText(context,"搜索",Toast.LENGTH_LONG).show()
        }

        tv_message_home.setOnClickListener {
            Toast.makeText(context,"进入消息中心",Toast.LENGTH_LONG).show()
        }
    }

    override fun initData(){
        println("加载数据")
        var url : String = Constants.HOME_URL
        HttpThread(url,handler).start()
    }

}