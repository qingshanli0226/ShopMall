package com.example.shopping_gradle.Controller.Fragment.ClassTwoFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter.TypeLeftAdapter
import com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter.TypeRightAdapter
import com.example.shopping_gradle.Model.Entity.MyleftTypeBean
import com.example.shopping_gradle.Model.MyInterface.TypeInteface
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.list_frag.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListFragment:Fragment() {
        var urls:Array<String> = arrayOf(MyConstants.SKIRT_URL,MyConstants.JACKET_URL,MyConstants.PANTS_URL
        ,MyConstants.OVERCOAT_URL,MyConstants.ACCESSORY_URL,MyConstants.BAG_URL,MyConstants.DRESS_UP_URL,MyConstants.HOME_PRODUCTS_URL
        ,MyConstants.STATIONERY_URL,MyConstants.DIGIT_URL,MyConstants.GAME_URL)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.list_frag, container, false)

        val left_List = view.left_List
        val right_List = view.right_List

        val linearLayoutManager:LinearLayoutManager= LinearLayoutManager(this.context)
        linearLayoutManager.orientation=LinearLayoutManager.VERTICAL
        left_List.layoutManager=linearLayoutManager

        val gridLayoutManager:GridLayoutManager= GridLayoutManager(context,3)
        right_List.layoutManager=gridLayoutManager

        initData(urls[0])
        return view
    }

    private fun initData(s: String) {

        val build = Retrofit.Builder()
            .baseUrl(MyConstants.BASE_URL_JSON).addConverterFactory(GsonConverterFactory.create())
            .build()
            build.create(TypeInteface::class.java).getLeftBaen(s).enqueue(object :Callback<MyleftTypeBean>{
            override fun onFailure(call: Call<MyleftTypeBean>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<MyleftTypeBean>?, response: Response<MyleftTypeBean>?) {

                val leftAdapter:TypeLeftAdapter= TypeLeftAdapter()
                view!!.left_List.adapter=leftAdapter


                initLeftListener(leftAdapter)

                val rightAdapter:TypeRightAdapter= TypeRightAdapter(context,response)
                view!!.right_List.adapter=rightAdapter

            }
        })
    }

    private fun initLeftListener(leftAdapter: TypeLeftAdapter) {
        leftAdapter.ClickItem(object :TypeLeftAdapter.leftClick{
            override fun setOnClick(index: Int) {
                leftAdapter.channelAdapter(index)
                if(index!=0){

                }
                    initData(urls[index])
                leftAdapter.notifyDataSetChanged()

            }
        })
    }
}