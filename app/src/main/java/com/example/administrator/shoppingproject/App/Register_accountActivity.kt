package com.example.administrator.shoppingproject.App

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.administrator.shoppingproject.Base.PhoneGreen
import com.example.administrator.shoppingproject.R
import com.example.day9application.DaoMaster
import com.example.day9application.PhoneGreenDao
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register_account.*

class Register_accountActivity : AppCompatActivity() {
    var arr=ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_account)
        val openHelper = DaoMaster.DevOpenHelper(this, "phone", null)
        val writableDatabase = openHelper.writableDatabase
        val daoMaster = DaoMaster(writableDatabase)
        val newSession = daoMaster.newSession()
        val phoneGreenDao = newSession.phoneGreenDao
        arr.add(R.drawable.ao)
        arr.add(R.drawable.ao2)
        arr.add(R.drawable.abc_btn_check_material)
        banner_sign.setBannerStyle(   BannerConfig.NUM_INDICATOR)
        banner_sign.setImages(arr)

        banner_sign.setDelayTime(2000)
        banner_sign.start()


       //注册
        but_sign.setOnClickListener {

            val phone =et_sign_phone_number.text.toString()
            val pwd =et_sign_pwd.text.toString()
            if (phone.equals("")||pwd.equals("")){
                Toast.makeText(this@Register_accountActivity,"用户名或密码不能为空",Toast.LENGTH_SHORT).show()
            }else{
                val green=PhoneGreen()
                green.phonenumber=phone
                green.password=pwd
                phoneGreenDao.insert(green)
                Toast.makeText(this@Register_accountActivity,"注册成功",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@Register_accountActivity,LoginActivity::class.java)
                startActivity(intent)
            }



        }




    }
    public class GlideImageLoader : ImageLoader(){
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

        }

    }
}

