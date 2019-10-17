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
import android.widget.Toast
import com.example.administrator.shoppingproject.Adpater.RecyclerAdpater
import com.example.administrator.shoppingproject.Adpater.XRecyclerAdpater
import com.example.administrator.shoppingproject.Base.Data
import com.example.administrator.shoppingproject.Base.GreenDao
import com.example.administrator.shoppingproject.Base.RecyclerBean
import com.example.administrator.shoppingproject.R
import com.example.day9application.DaoMaster
import com.example.day9application.GreenDaoDao
import com.google.gson.Gson
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlinx.android.synthetic.main.fra1.*
import kotlinx.android.synthetic.main.fra1.view.*
import okhttp3.*
import java.io.IOException

class FraHome : Fragment() {
    lateinit var green:GreenDaoDao
    val arr=ArrayList<Data>()
    //判断上拉刷新下拉加载的变量
    var judge:Int = 1
    val list=ArrayList<GreenDao>()
    internal  val handler= @SuppressLint("HandlerLeak")
     object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //greendao 数据库
        val openHelper = DaoMaster.DevOpenHelper(context, "sh",null)
        val writableDatabase = openHelper.writableDatabase
        val daoMaster = DaoMaster(writableDatabase)
        val newSession = daoMaster.newSession()
        green = newSession.greenDaoDao
        green.deleteAll()



        val adp= XRecyclerAdpater(arr,context)
        val builder = OkHttpClient.Builder()
        val build = builder.build()
        val re = Request.Builder()
        re.url("http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page="+judge)
        val bu=re.build()
        val call = build.newCall(bu)





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
                        green.insert(greenadd)
                    }

                    rv_home.layoutManager=LinearLayoutManager(context)
                    rv_home.adapter=adp
                    adp.notifyDataSetChanged()

                }
            }

        })

        return inflater.inflate(R.layout.fra1,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {




        //XRecyclerAdpater上啦刷新下拉加载方法
        view.rv_home.setPullRefreshEnabled(true)
        view.rv_home.setLoadingMoreEnabled(true)
        view.rv_home.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        view.rv_home.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        view.rv_home.setLoadingListener(object : XRecyclerView.LoadingListener{
            override fun onLoadMore() {
                //加载
            }

            override fun onRefresh() {
                //刷新
            }

        })


        view.but_seek.setOnClickListener {
            Toast.makeText(context,"1111",Toast.LENGTH_SHORT).show()
            if (view.tv_search_home.text.toString().isNullOrBlank()){
                for (greenDao in green.queryRaw("Where  1==1")) {
                    println("SSH名字是${greenDao.tittle}内容是${greenDao.text}")
                    Toast.makeText(context, "查找成功${greenDao.tittle}内容是${greenDao.text}", Toast.LENGTH_SHORT).show()

                }

            }else {


                val queryRaw = green.queryRaw("Where Tittle like ?", view.tv_search_home.text.toString()+'%')


                for (greenDao in queryRaw) {
                    println("SSH名字是${greenDao.tittle}内容是${greenDao.text}")
                    Toast.makeText(context, "查找成功${greenDao.tittle}内容是${greenDao.text}", Toast.LENGTH_SHORT).show()

                }

            }
        }
    }

}