package com.atguigu.shoppingmall.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyAppliction : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        // 获取全局上下文
        lateinit var context:Context
        fun getContex():Context{
            return context
        }
    }

}
