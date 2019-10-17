package jni.example.atguigu.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.GoodsBean
import jni.example.atguigu.home.Bean.ResultBean
import jni.example.atguigu.home.adapter.RecycleAdpter
import jni.example.atguigu.utils.Constants

class HomeFragment<T> : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_home,null)
        var rv_home:RecyclerView=view.findViewById(R.id.rv_home)
       var tv_search_home:TextView=view.findViewById(R.id.tv_search_home)

       var tv_message_home:TextView=view.findViewById(R.id.tv_message_home)
        var ctx:Context=view.context
        rv_home.layoutManager=LinearLayoutManager(ctx)
        var list:ArrayList<GoodsBean.Result> = arrayListOf()
        OkGo.get<String>(Constants.BASE+Constants.BASE_URL_JSON+Constants.HOME_URL)
        (object : StringCallback() {
            override fun onSuccess(response: Response<String>) {
                val body = response.body()
                var result:GoodsBean=Gson().fromJson(body,GoodsBean::class.java)
                println("wzy:${result.code}")
                list.add(result.result)
                var adpter:RecycleAdpter= RecycleAdpter(list)
                rv_home.adapter=adpter
                return
            }
        })

        //
        return view
    }
}

