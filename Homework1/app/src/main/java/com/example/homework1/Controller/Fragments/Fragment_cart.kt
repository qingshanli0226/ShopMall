package com.example.homework1.Controller.Fragments

import android.view.Gravity
import android.view.View
import android.widget.TextView


class Fragment_cart : BaseFragment() {
    lateinit var textView: TextView
    override fun initView(): View {
        textView = TextView(mContext)
        textView.gravity = Gravity.CENTER
        textView.textSize = 25f
        return textView
    }

    override fun initData(){
        textView.text = "购物车"
    }
}