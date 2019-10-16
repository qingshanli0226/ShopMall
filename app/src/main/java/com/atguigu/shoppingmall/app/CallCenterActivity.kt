package com.atguigu.shoppingmall.app

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.utils.Constants
import kotlinx.android.synthetic.main.activity_call_center.*


class CallCenterActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_center)
        setWebView(Constants.CALL_CENTER)
    }

    private fun setWebView(url: String?) {

        if (url != null) {
            webview!!.loadUrl(url)
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            webview!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url)
                    return true
                }
            }
            //启用支持javascript
            val settings = webview!!.settings
            settings.javaScriptEnabled = true

            //优先使用缓存
            webview!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
    }
}
