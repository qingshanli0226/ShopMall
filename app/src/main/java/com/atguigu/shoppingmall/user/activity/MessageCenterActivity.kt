package com.atguigu.shoppingmall.user.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

import com.atguigu.shoppingmall.R


class MessageCenterActivity : Activity() {
    private var ib_login_back: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesaage_center)
        ib_login_back = findViewById(R.id.ib_login_back)

        ib_login_back!!.setOnClickListener { finish() }
    }
}
