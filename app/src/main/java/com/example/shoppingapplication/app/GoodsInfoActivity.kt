package com.example.shoppingapplication.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.shoppingapplication.R
import com.example.shoppingapplication.home.bean.GoodsBean
import com.example.shoppingapplication.utils.Constants
import kotlinx.android.synthetic.main.activity_goods_info.*
import kotlinx.android.synthetic.main.more_layout.*

class GoodsInfoActivity : AppCompatActivity() {

    var goodsBeans : MutableList<GoodsBean> = mutableListOf()
    lateinit var goods_bean:GoodsBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_info)

        ib_good_info_more.setOnClickListener {
            if (ll_root.getVisibility() == View.VISIBLE) {
                ll_root.setVisibility(View.GONE);
            } else {
                ll_root.setVisibility(View.VISIBLE);
            }
        }
        btn_more.setOnClickListener {
            ll_root.setVisibility(View.GONE);
        }
        tv_more_share.setOnClickListener {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        }
        tv_more_search.setOnClickListener {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        }
        tv_more_home.setOnClickListener {
            Constants.isBackHome = true
        }
        tv_good_info_callcenter.setOnClickListener {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }
    }
}
