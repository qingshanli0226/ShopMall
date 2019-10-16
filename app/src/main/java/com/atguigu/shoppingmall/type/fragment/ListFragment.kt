package com.atguigu.shoppingmall.type.fragment


import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.type.adapter.TypeLeftAdapter
import com.atguigu.shoppingmall.type.adapter.TypeRightAdapter
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.type.bean.TypeBean
import com.atguigu.shoppingmall.utils.Constants
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

import okhttp3.Call
import okhttp3.Request

/**
 * 分类页面
 * A simple [Fragment] subclass.
 */
@SuppressLint("ValidFragment")
class ListFragment(var mainActivity: MainActivity) : BaseFragment() {
    private val fl_list_container: FrameLayout? = null
    private var lv_left: ListView? = null
    private var rv_right: RecyclerView? = null
    private var result: List<TypeBean.ResultBean>? = null

    private val urls = arrayOf(Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL, Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL, Constants.DIGIT_URL, Constants.GAME_URL)
    //
    //

    private lateinit var leftAdapter: TypeLeftAdapter
    private var isFirst = true

    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_list, null)
        lv_left = view.findViewById<View>(R.id.lv_left) as ListView
        rv_right = view.findViewById<View>(R.id.rv_right) as RecyclerView
        return view
    }

    override fun initData() {
        super.initData()
        //联网请求
        getDataFromNet(urls[0])
    }

    /**
     * 具体的联网请求代码
     * @param url
     */
    fun getDataFromNet(url: String) {
        OkHttpUtils
                .get()
                .url(url)
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
            //两位请求成功

            when (id) {
                100 ->
                    //                    Toast.makeText(mContext, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        //解析数据
                        processData(response)
                        if (isFirst) {
                            leftAdapter = TypeLeftAdapter(mainActivity)
                            lv_left!!.adapter = leftAdapter
                        }


                        initListener(leftAdapter)

                        //解析右边数据
                        val rightAdapter = TypeRightAdapter(mainActivity, result)
                        rv_right!!.adapter = rightAdapter

                        val manager = GridLayoutManager(activity, 3)

                        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                return if (position == 0) {
                                    3
                                } else {
                                    1
                                }
                            }
                        }
                        rv_right!!.layoutManager = manager
                    }
                101 -> Toast.makeText(mainActivity, "https", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initListener(adapter: TypeLeftAdapter) {
        //点击监听
        lv_left!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            adapter.changeSelected(position)//刷新
            if (position != 0) {
                isFirst = false
            }
            getDataFromNet(urls[position])
            leftAdapter.notifyDataSetChanged()
        }

        //选中监听
        lv_left!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                adapter.changeSelected(position)//刷新

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

    private fun processData(json: String) {
        val gson = Gson()
        val typeBean = gson.fromJson<TypeBean>(json, TypeBean::class.java)
        result = typeBean.result
    }
}