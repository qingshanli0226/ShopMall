package com.bwie.shopper.utils

import android.app.Activity
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.Callback
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.HttpParams
import kotlin.properties.ReadOnlyProperty


object  OkGoUtils  {
    lateinit var url:String
    lateinit var params: HttpParams
//    lateinit var clazz: Class
    var methodType:Int?= null

    fun <T>getRequest(url:String,call: Callback<T>){
        OkGo.get<T>(url).execute(call)
    }

}