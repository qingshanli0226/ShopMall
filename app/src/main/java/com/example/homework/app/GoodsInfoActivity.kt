package com.example.homework.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homework.R
import com.example.homework.home.adapter.HomeRecycleAdapter
import com.example.homework.home.bean.GoodsBean
import com.example.homework.utils.Constants
import kotlinx.android.synthetic.main.activity_goods_info.*
import kotlinx.android.synthetic.main.more_layout.*

class GoodsInfoActivity : AppCompatActivity() {
    var goods_bean: GoodsBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_info)
        //返回按钮监听
        ib_good_info_back.setOnClickListener {
            finish()
        }

        ib_good_info_more.setOnClickListener {
            if (ll_root.visibility == VISIBLE){
                ll_root.visibility = GONE
            }else{
                ll_root.visibility = VISIBLE
            }
        }




        val intent = getIntent()
        goods_bean = intent.getParcelableExtra<GoodsBean>(HomeRecycleAdapter.GOODS_BEAN)
        //本地获取存储的数据
        goods_bean?.let { setDataFormartView(it) }
    }

    fun setDataFormartView(goodsBean: GoodsBean) {
        Glide.with(this).load(Constants.BASE_URl_IMAGE + goodsBean.figure).into(iv_good_info_image)
        tv_good_info_name.text = goodsBean.name
        tv_good_info_price.text = "￥" + goodsBean.cover_price

        setWebView(goodsBean.product_id)
    }

    fun setWebView(product_id: String) {
        if (!product_id.isNullOrBlank()) {
            wb_good_info_more.loadUrl("http://www.atguigu.com")
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wb_good_info_more.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (view != null) {
                        view.loadUrl(url)
                    }
                    return true
                }
            }

            //启用支持javascript
            val settings = wb_good_info_more.settings
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true

            //优先使用缓存
            wb_good_info_more.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
    }
}
