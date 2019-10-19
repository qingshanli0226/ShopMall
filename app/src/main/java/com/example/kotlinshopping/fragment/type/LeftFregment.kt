package com.example.kotlinshopping.fragment.type

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.adapter.type.TypeLeftAdapter
import com.example.kotlinshopping.adapter.type.TypeRightAdapter
import com.example.kotlinshopping.adapter.type.TypeRightAdapter2
import com.example.kotlinshopping.bean.TypeBean
import com.example.kotlinshopping.bean.TypeResult
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response

class LeftFregment : Fragment() {
    val urls = arrayOf<String>(
        Constants.SKIRT_URL,
        Constants.JACKET_URL,
        Constants.PANTS_URL,
        Constants.OVERCOAT_URL,
        Constants.ACCESSORY_URL,
        Constants.BAG_URL,
        Constants.DRESS_UP_URL,
        Constants.HOME_PRODUCTS_URL,
        Constants.STATIONERY_URL,
        Constants.DIGIT_URL,
        Constants.GAME_URL
    )
    var rootView:View?= null
    lateinit var rightAdapter:TypeRightAdapter2
    lateinit var rv_right:RecyclerView
    var data:List<TypeResult> = listOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_list,container,false)
        var adapter = TypeLeftAdapter(context!!)
        rv_right = rootView!!.findViewById(R.id.rv_right)
        rootView!!.findViewById<ListView>(R.id.lv_left).adapter = adapter
        rootView!!.findViewById<ListView>(R.id.lv_left).onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                adapter.selectPositon(position)
                loadData(urls[position])
            }
        }

        loadData(urls[0])
        Log.d("LW---网址", loadData(urls[0]).toString())
        var manager = GridLayoutManager(activity,3)
        manager.setSpanSizeLookup(object : GridLayoutManager.SpanSizeLookup() {
         override  fun getSpanSize(position: Int): Int {
                return if (position == 0) {
                    3
                } else {
                    1
                }
            }
        })
        rv_right.layoutManager = manager

        return rootView
    }

    private fun loadData(url:String) {
        OkGo.get<String>(url)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var json = response!!.body()
                    Log.d("LW--数据", response.body())

                    val result = Gson().fromJson<TypeBean>(json, TypeBean::class.java).result
                   data = result
                    rightAdapter = TypeRightAdapter2(context!!,data)
                    rv_right.adapter = rightAdapter
                    rightAdapter.notifyDataSetChanged()
                }

            })
    }
}