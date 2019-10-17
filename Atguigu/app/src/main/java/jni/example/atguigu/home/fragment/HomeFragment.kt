package jni.example.atguigu.home.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.ResultBean
import jni.example.atguigu.home.adapter.HomeAdapter
import jni.example.atguigu.utils.Constants
import jni.example.atguigu.utils.OkUtlis


class HomeFragment : Fragment(){
    var resultBean:ResultBean? = null
    lateinit var homeAdapter:HomeAdapter
    var handler = object :Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what==0){
                Log.d("lhf ",msg.obj.toString())
                val resultJson = msg.obj.toString()
                var resultBean = Gson().fromJson<ResultBean>(resultJson,ResultBean::class.java)
                activity!!.runOnUiThread (Runnable {
                    homeAdapter.bean = resultBean
                    homeAdapter.notifyDataSetChanged()
                })
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_home,null)

        homeAdapter = HomeAdapter(resultBean, activity)
        var rv_home = view.findViewById<RecyclerView>(R.id.rv_home)
        rv_home.layoutManager = LinearLayoutManager(context)
        rv_home.adapter = homeAdapter
        onGetJson()

        return view
    }

    fun onGetJson(){
        OkUtlis.doGet(Constants.HOME_URL,object :OkUtlis.MyCallback{
            override fun success(resultJson: String) {
                val message = Message.obtain()
                message.what=0
                message.obj = resultJson
                handler.handleMessage(message)
            }

            override fun error(error: String) {
                Log.d("zzz ",error)
            }

        })
    }
}