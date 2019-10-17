package com.example.shoppingapplication.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppingapplication.R
import kotlinx.android.synthetic.main.activity_login.*
import android.text.InputType



class LoginActivity : AppCompatActivity() {

    var count:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.shoppingapplication.R.layout.activity_login)

        ib_login_visible.setOnClickListener {
            count++
            if (count % 2 === 0) {
                ib_login_back.setBackgroundResource(R.drawable.new_password_drawable_invisible)
                et_login_pwd.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            } else {
                ib_login_back.setBackgroundResource(R.drawable.new_password_drawable_visible)
                et_login_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            }
        }

    }
}
