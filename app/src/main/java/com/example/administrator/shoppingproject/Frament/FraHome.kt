package com.example.administrator.shoppingproject.Frament

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.shoppingproject.Adpater.RecyclerAdpater
import com.example.administrator.shoppingproject.Base.Data
import com.example.administrator.shoppingproject.Base.GreenDao
import com.example.administrator.shoppingproject.Base.RecyclerBean
import com.example.administrator.shoppingproject.R
import com.example.day9application.DaoMaster
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fra1.*
import okhttp3.*
import java.io.IOException

class FraHome : Fragment() {
    val arr=ArrayList<Data>()
    val list=ArrayList<GreenDao>()
    internal  val handler= @SuppressLint("HandlerLeak")
     object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adp=RecyclerAdpater(arr,context)
        val builder = OkHttpClient.Builder()
        val build = builder.build()
        val re = Request.Builder()
        re.url("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
        val bu=re.build()
        val call = build.newCall(bu)

        //greendao 数据库
        val openHelper = DaoMaster.DevOpenHelper(context, "sh",null)
        val writableDatabase = openHelper.writableDatabase
        val daoMaster = DaoMaster(writableDatabase)
        val newSession = daoMaster.newSession()
        val green = newSession.greenDaoDao

//        but_seek.setOnClickListener {
//            if (tv_search_home.text.toString().equals("")){
//                for (greenDao in green.queryRaw("Where  1==1")) {
//                    println("名字是${greenDao.tittle}内容是${greenDao.text}")
//                }
//
//            }else {
//                for (greenDao in green.queryRaw("Where name like ?",tv_search_home.text.toString())) {
//                    println("查到的数据模糊查询标题${greenDao.tittle}内容${greenDao.text}")
//                }
//
//            }
//        }

        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                 //失败
            }

            override fun onResponse(call: Call, response: Response) {
                //成功
                val me=Message()
                me.obj= response.body()!!.string()
                handler.post(){
                    val s1:String=me.obj.toString()
                    val gson=Gson()
                    val bean = gson.fromJson(s1, RecyclerBean::class.java)
                    arr.addAll(bean.data)
                    for (i in 0..arr.size-1) {
                        val greenadd=GreenDao()
                        greenadd.tittle=arr.get(i).title
                        greenadd.text=arr.get(i).food_str
                        list.add(greenadd)
                    }

                    rv_home.layoutManager=LinearLayoutManager(context)
                    rv_home.adapter=adp
                    adp.notifyDataSetChanged()

                }
            }

        })

        return LayoutInflater.from(context).inflate(R.layout.fra1,container,false)

    }
}