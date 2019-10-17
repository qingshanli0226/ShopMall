package com.example.myshopping.home.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment
import com.example.myshopping.home.Adapter.MyAdapter2
import com.example.myshopping.home.Adapter.MyAdpater
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.home.bean.JsonBean
import com.example.myshopping.home.bean.UrlBean

import com.example.myshopping.utils.Constants
import com.example.myshopping.utils.OkHttpUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import okhttp3.Call
import org.json.JSONObject
import java.io.IOException

class HomeFragment : Fragment() {

    private var list = mutableListOf<HomeBean>()
    private var firstlist = mutableListOf<UrlBean>()
    private var secondlist = mutableListOf<UrlBean>()
    private var thirdlist = mutableListOf<UrlBean>()
    private var fourthlist = mutableListOf<UrlBean>()
    private var fifthlist = mutableListOf<UrlBean>()
    private var sixthlist = mutableListOf<UrlBean>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.fragment_home, null)
        OkHttpUtils.instance.get(Constants.HOME_URL).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                println("zjw_ ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonStr = response.body()?.string()
                val jsonObject = JSONObject(jsonStr)
                val jsonObject1 = jsonObject.getJSONObject("result")
                val jsonArray = jsonObject1.getJSONArray("act_info")
                for (i in 0..jsonArray.length() - 1) {
                    val jsonObjects = jsonArray[i] as JSONObject
                    val icon_url = jsonObjects.getString("icon_url")
                    println("zjw1_ : icon_url = $icon_url")
                    thirdlist.add(UrlBean(icon_url))
                }


                val jsonArray1 = jsonObject1.getJSONArray("banner_info")
                for (i in 0..jsonArray1.length() - 1) {
                    val banner_info = jsonArray1[i] as JSONObject
                    val image = banner_info.getString("image")
                    firstlist.add(UrlBean(image))
                    println("zjw2_ : image = $image")
                }


                val channel_info = jsonObject1.getJSONArray("channel_info")
                for (i in 0..channel_info.length() - 1) {
                    val jsonObject2 = channel_info[i] as JSONObject
                    val channel_name = jsonObject2.getString("channel_name")
                    val image = jsonObject2.getString("image")
                    secondlist.add(UrlBean(channel_name, image))
                    println("zjw3_ : channel_name = $channel_name")
                    println("zjw3_ : image = $image")
                }


                val hot_info = jsonObject1.getJSONArray("hot_info")
                for (i in 0..hot_info.length() - 1) {
                    val jsonObject2 = hot_info[i] as JSONObject
                    val cover_price = jsonObject2.getString("cover_price")
                    val figure = jsonObject2.getString("figure")
                    val name = jsonObject2.getString("name")
                    sixthlist.add(UrlBean(cover_price, figure, name))
                    println("zjw4_ : cover_price = $cover_price")
                    println("zjw4_ : figure = $figure")
                    println("zjw4_ : name = $name")
                }

                val recommend_info = jsonObject1.getJSONArray("recommend_info")
                for (i in 0..recommend_info.length() - 1) {
                    val jsonObject2 = recommend_info[i] as JSONObject
                    val cover_price = jsonObject2.getString("cover_price")
                    val figure = jsonObject2.getString("figure")
                    val name = jsonObject2.getString("name")
                    fifthlist.add(UrlBean(cover_price, figure, name))
                    println("zjw5_ : cover_price = $cover_price")
                    println("zjw5_ : figure = $figure")
                    println("zjw5_ : name = $name")
                }

                val seckill_info = jsonObject1.getJSONObject("seckill_info")
                val jsonArray2 = seckill_info.getJSONArray("list")
                for (i in 0..jsonArray2.length() - 1) {
                    val jsonObject2 = jsonArray2[i] as JSONObject
                    val cover_price = jsonObject2.getString("cover_price")
                    val figure = jsonObject2.getString("figure")
                    val origin_price = jsonObject2.getString("origin_price")
                    println("zjw6_ : cover_price = $cover_price")
                    println("zjw6_ : figure = $figure")
                    println("zjw6_ : origin_price = $origin_price")
                    fourthlist.add(UrlBean(cover_price, figure, origin_price))
                }
                list.add(HomeBean(1, firstlist))
//                list.add(HomeBean(2, secondlist))
//                list.add(HomeBean(3, thirdlist))
//                list.add(HomeBean(4, fourthlist))
//                list.add(HomeBean(5, fifthlist))
//                list.add(HomeBean(6, sixthlist))
//                val myAdpater = MyAdpater(activity!!, list)
//                rv_home.layoutManager = LinearLayoutManager(activity!!)
//                rv_home.adapter = myAdpater
                val myAdapter2 = MyAdapter2()
                myAdapter2.updataData(list as MutableList<Any>)
                rv_home.layoutManager = LinearLayoutManager(activity!!)
                rv_home.adapter = myAdapter2
            }
        })

        return view
    }


}