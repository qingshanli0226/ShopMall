package jni.example.atguigu.type.fragment

import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jni.example.atguigu.R
import jni.example.atguigu.base.BaseFragment
import jni.example.atguigu.type.adapter.Type_List_Left_Adapter
import jni.example.atguigu.utils.Constants
import jni.example.atguigu.utils.OkUtlis
import kotlinx.android.synthetic.main.fragment_type_list_item.view.*

class Type_List_Fragment : BaseFragment() {
    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        var view: View = inflater.inflate(R.layout.fragment_type_list_item, null)
        var array: Array<String> =
            arrayOf("小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区")
        var arrayUrl: Array<String> = arrayOf(
            Constants.SKIRT_URL,
            Constants.JACKET_URL,
            Constants.PANTS_URL,
            Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL,
            Constants.BAG_URL,
            Constants.DRESS_UP_URL,
            Constants.HOME_PRODUCTS_URL,
            Constants.STATIONERY_URL,
            Constants.DIGIT_URL,
            Constants.GAME_URL
        )
        var leftAdapter = Type_List_Left_Adapter(context!!, array)
        view.type_lv.adapter = leftAdapter

        view.type_lv.setOnItemClickListener { parent, view, position, id ->
            leftAdapter.setmSelect(position)
        }
        return view
    }

    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 100) {
                Log.d("lhf", msg.obj.toString())
            }
        }
    }

    fun onGetJson(url: String) {
        OkUtlis.doGet("${Constants.BASE}$url", object : OkUtlis.MyCallback {
            override fun success(string: String) {
                val message = Message.obtain()
                message.obj = string
                message.what = 100
                handler.handleMessage(message)
            }

            override fun error(error: String) {

            }
        })
    }
}