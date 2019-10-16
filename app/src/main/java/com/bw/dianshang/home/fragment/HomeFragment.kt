package com.bw.dianshang.home.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bw.dianshang.*
import com.bw.dianshang.home.adapter.HomeRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Result


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view:View = inflater.inflate(R.layout.fragment_home, container, false)

        initData()
        return view
    }

    private fun initData() {
        Retrofit.Builder()
            .baseUrl(Constants.BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Retrofits::class.java)
            .getBean(Constants.HOME_URL)
            .enqueue(object : Callback<Bean> {
                override fun onResponse(call: Call<Bean>, response: Response<Bean>) {
                    var list:MutableList<com.bw.dianshang.Result> = response.body().result as MutableList<com.bw.dianshang.Result>
                    var homeRecyclerAdapter: HomeRecyclerAdapter =
                        HomeRecyclerAdapter(activity!!.applicationContext, list)
                    println("xxx 请求成功 ${response.body().result}")
                }

                override fun onFailure(call: Call<Bean>, t: Throwable) {
                    println("xxx 请求失败")
                }
            })
    }

}
