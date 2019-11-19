package com.example.administrator.shoppingproject.App

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.WindowManager
import android.widget.Toast
import com.example.administrator.shoppingproject.R
import com.example.day9application.DaoMaster
import com.example.day9application.PhoneGreenDao
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register_account.*

class LoginActivity : AppCompatActivity() {
    lateinit var phonegreen: PhoneGreenDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//            window.statusBarColor = resources.getColor(R.color.colorPrimary)
//
//        } else {
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            var viewG = window.decorView
//            viewG.setBackgroundColor(Color.RED)
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.greencolor)
        }

        ib_login_back.setOnClickListener {
            //退出
            finish()
        }

        //通知
        val builder = NotificationCompat.Builder(this, "ig")
                .setSmallIcon(R.drawable.new_user_login_icon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ao, null))
                .setContentTitle("单邵华")
                .setContentText("这个是内容")
                .setPriority(2)
        var intent = Intent(this, this.javaClass)
        var pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_ONE_SHOT)
        builder.setContentIntent(pendingIntent)
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
//登录

            btn_login.setOnClickListener {
                val phone = et_login_phone.text.toString()
                val pwd = et_login_pwd.text.toString()
                val queryRaw = phonegreen.queryRaw("Where phonenumber =? and password =?", phone, pwd)

                val empty = queryRaw.isEmpty()
                if (empty == true) {
                    //空的
                    Toast.makeText(this@LoginActivity, "没有此人", Toast.LENGTH_SHORT).show()
                } else {
                    for (phoneGreen in queryRaw) {
                        Toast.makeText(this@LoginActivity, "查询成功", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

            }

//            val phone = et_login_phone.text.toString()
//            val pass = et_login_pwd.text.toString()
//            val queryRaw = phoneGreenDao.queryRaw("Where phonenumber =? and Where password =?", phone, pass)
//            for (phoneGreen in queryRaw) {
//                Toast.makeText(this@Register_accountActivity,"查询成功", Toast.LENGTH_SHORT).show()
//                val intent=Intent(this@Register_accountActivity,MainActivity::class.java)
//                startActivity(intent)
//            }

            //创建数据库
            val openHelper = DaoMaster.DevOpenHelper(this@LoginActivity, "phone", null)
            val writableDatabase = openHelper.writableDatabase
            val daoMaster = DaoMaster(writableDatabase)
            val newSession = daoMaster.newSession()
            phonegreen = newSession.phoneGreenDao

            tv_login_forget_pwd.setOnClickListener {
                val intent = Intent(this@LoginActivity, Register_accountActivity::class.java)
                startActivity(intent)
            }


        }
    }
}
