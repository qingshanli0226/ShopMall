package com.example.homework.app

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {
    //是否显示密码
    var isShowPassword:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //返回按钮
        ib_login_back.setOnClickListener { finish() }

        //密码是否明文显示
        ib_login_visible.setOnClickListener {
            isShowPassword = !isShowPassword
            if(isShowPassword){
                ib_login_visible.backgroundResource = R.drawable.new_password_drawable_invisible
                et_login_pwd.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }else{
                ib_login_visible.backgroundResource = R.drawable.new_password_drawable_visible
                et_login_pwd.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }


        }

        //登录
        btn_login.setOnClickListener { loginButton()
            toast("点击了登录") }
    }

    private fun loginButton() {


    }
}

