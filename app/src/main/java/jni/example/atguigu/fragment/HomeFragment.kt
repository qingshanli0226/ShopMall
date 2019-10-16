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
import jni.example.atguigu.R

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
        //
        return view
    }
    fun getDataFroNet(){

    }
    fun initData(){
          getDataFroNet()
    }
}