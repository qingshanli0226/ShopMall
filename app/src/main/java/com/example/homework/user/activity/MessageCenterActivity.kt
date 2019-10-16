package com.example.homework.user.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R
import kotlinx.android.synthetic.main.activity_mesaage_center.*

class MessageCenterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mesaage_center )
        //返回按钮
        ib_login_back.setOnClickListener { finish() }


    }
}