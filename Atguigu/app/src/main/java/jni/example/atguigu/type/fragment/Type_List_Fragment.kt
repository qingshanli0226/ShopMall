package jni.example.atguigu.type.fragment

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jni.example.atguigu.R
import jni.example.atguigu.base.BaseFragment
import jni.example.atguigu.utils.Constants
import jni.example.atguigu.utils.OkUtlis

class Type_List_Fragment: BaseFragment() {
    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        var view:View = inflater.inflate(R.layout.fragment_type_list_item,null)
        return view
    }

    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            if(msg.what==100){
                Log.d("lhf",msg.obj.toString())
            }
        }
    }

    fun onGetJson(){
        OkUtlis.doGet("${Constants.BASE}${Constants.TAG_URL}",object : OkUtlis.MyCallback{
            override fun success(string: String) {
                val message = Message.obtain()
                message.obj=string
                message.what=100
                handler.handleMessage(message)
            }

            override fun error(error: String) {

            }
        })
    }
}