package com.example.homework.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework.R
import com.example.homework.home.adapter.HomeRecycleAdapter
import com.example.homework.home.bean.ResultBean
import com.example.homework.user.activity.MessageCenterActivity
import com.example.homework.utils.Constants
import com.example.homework.utils_internet.Utils_Internet
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.titlebar.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class HomeFragment : Fragment(), Utils_Internet.CallBackData {
    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this!!.activity!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflate = inflater.inflate(R.layout.fragment_home, null)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //搜索框
        tv_search_home.setOnClickListener { toast("搜索") }
        //消息
        tv_message_home.setOnClickListener { startActivity<MessageCenterActivity>() }


        //请求数据
        Utils_Internet.getHomeData(Constants.HOME_URL, this)
        val gridLayoutManager = GridLayoutManager(mContext, 1)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                if (position <= 3){
                    ib_top.visibility = View.GONE
                }else{
                    ib_top.visibility = View.VISIBLE
                }
                return 1
            }
        }
        rv_home.layoutManager = gridLayoutManager
    }


    //数据请求成功
    override fun onSuccess(t: ResultBean) {
        //添加适配器
        rv_home.adapter = HomeRecycleAdapter(mContext, t)
    }

    //数据请求失败
    override fun onError(e: Throwable) {
        toast(e.printStackTrace().toString())
    }
}