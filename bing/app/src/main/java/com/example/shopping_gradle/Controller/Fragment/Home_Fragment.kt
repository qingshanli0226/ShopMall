package com.example.shopping_gradle.Controller.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Controller.Activity.MessageActivity
import com.example.shopping_gradle.Controller.Adapter.HomeAdapter.Home_RecyclerAdapter
import com.example.shopping_gradle.Model.Entity.ResultBean
import com.example.shopping_gradle.Model.MyInterface.HomeInterFace
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.home_frag.*
import kotlinx.android.synthetic.main.home_titlebar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Home_Fragment:Fragment() {

        lateinit var resultBean:ResultBean
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View= inflater.inflate(R.layout.home_frag,container,false)

        InItData()
        return view
    }



    private fun InItData() {

        val homeInterFace =
            Retrofit.Builder().baseUrl(MyConstants.BASE).addConverterFactory(GsonConverterFactory.create()).build()
                .create(
                    HomeInterFace::class.java
                )
              homeInterFace.homeDataCall(MyConstants.HOME_URL).enqueue(object : Callback<ResultBean> {
            override fun onResponse(call: Call<ResultBean>, response: Response<ResultBean>) {


                if(response!=null){
                    processData(response)

                    var gridManager:GridLayoutManager = GridLayoutManager(context,1)
                    var homeAdapter: Home_RecyclerAdapter =
                        Home_RecyclerAdapter(
                            context!!,
                            resultBean
                        )
                    home_RecyclerView.adapter=homeAdapter
                    gridManager.setSpanSizeLookup(object :GridLayoutManager.SpanSizeLookup(){
                        override fun getSpanSize(postion: Int): Int {
                            if (postion<=3){
                                hom_Top.visibility=View.GONE
                            }else{
                                hom_Top.visibility=View.VISIBLE
                            }
                            return 1
                        }
                    })


                    home_RecyclerView.layoutManager=gridManager
                    initListener()

                }


            }

            override fun onFailure(call: Call<ResultBean>, t: Throwable) {
                Log.d("##",call.toString())
            }
        })
    }

    private fun processData(json: Response<ResultBean>) {
        if(!TextUtils.isEmpty(json.toString())){




            resultBean=ResultBean(json.body().code,json.body().msg,json.body().result)
            val banner_info = json.body().result.banner_info
            resultBean.result.banner_info=banner_info
//            for (item in 0 until json.body().result.banner_info.size ){
//                val value = json.body().result.banner_info.get(item).value
//            }




            val act_info = json.body().result.act_info
            val channel_info = json.body().result.channel_info
            val hot_info = json.body().result.hot_info
            val recommend_info = json.body().result.recommend_info


            val seckill_info = json.body().result.seckill_info


        }

    }

    private fun initListener() {
        hom_Top.setOnClickListener {
            home_RecyclerView.scrollToPosition(0)
        }
        home_Message.setOnClickListener {
            startActivity(Intent(activity,MessageActivity::class.java))
        }
    }
}