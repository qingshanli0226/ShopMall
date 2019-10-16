package com.example.administrator.shoppingproject.App

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.shoppingproject.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ib_login_back.setOnClickListener {
            //退出
            finish()
        }
        val user=et_login_phone.text
        val pass=et_login_pwd.text


    }
}
