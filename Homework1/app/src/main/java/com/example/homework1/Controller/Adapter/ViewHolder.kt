package com.example.homework1.Controller.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.homework1.R
import com.example.homework1.Utils.CardTransformer
import com.example.homework1.Utils.Constants
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import org.json.JSONObject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),MyAdapter.OnItemClick{


    var map : HashMap<Int,View> = hashMapOf()
    var context : Context? = null
    var dataz : ArrayList<Map<String,Object>> = arrayListOf()

    lateinit var handler : Handler
    lateinit var myAdapter: MyAdapter

    private fun getView(id : Int) : View?{
        var view:View? = map.get(id)
        if(view==null){
            view = itemView.findViewById(id)
            map.put(id,view)
        }
        return view
    }

    fun setText(id : Int,text : String){
        var  textView : TextView = getView(id) as TextView
        if(textView!=null){
            textView.text = text
        }
    }

    fun setImageWithUrl(id : Int,url : String,context : Context){
        var imageView : ImageView = getView(id) as ImageView
        if(imageView!=null){
            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    }

    fun setBanner(id : Int,images : ArrayList<String>){
        var banner : Banner = getView(id) as Banner

        if(banner!=null){
            banner.setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    Glide.with(context)
                        .load(path)
                        .into(imageView)
                }
            })
            banner.setDelayTime(3000)
            banner.isAutoPlay(true)
            banner.setIndicatorGravity(BannerConfig.CENTER)
            banner.setBannerAnimation(Transformer.Default)
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            banner.setImages(images)
            banner.start()
        }
    }


    fun setRecycler(id: Int,datas : ArrayList<Map<String,Object>>,context: Context?){
        var recyclerView : RecyclerView = getView(id) as RecyclerView

        if(recyclerView!=null && context!=null){
            var gridLayoutManager : GridLayoutManager = GridLayoutManager(context,5)
            gridLayoutManager.orientation = RecyclerView.VERTICAL
            recyclerView.layoutManager = gridLayoutManager

            var myAdapter:MyAdapter = object : MyAdapter(){
                override fun bind(holder: ViewHolder, position: Int) {
                    holder.setImageWithUrl(R.id.iv_icon,"${datas[position].get("image")}",context)
                    holder.setText(R.id.tv_title,"${datas[position].get("channel_name")}")
                }
            }
            recyclerView.adapter = myAdapter
            myAdapter.refresh(datas)
            myAdapter.setClick(this@ViewHolder)
        }
    }

    fun setRecycler2(id: Int,jsonObject: JSONObject,context: Context?){
        var recyclerView : RecyclerView = getView(id) as RecyclerView

        handler = @SuppressLint("HandlerLeak")
        object : Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){
                    100 -> refresh()
                    200 -> setTime(msg)
                }
            }
        }

        this.context = context

        if(recyclerView!=null && context!=null){
            val manager = LinearLayoutManager(context)
            manager.orientation = RecyclerView.VERTICAL
            recyclerView.layoutManager = manager
            myAdapter = object : MyAdapter() {
                override fun bind(holder: ViewHolder, position: Int) {
                    val type = dataz[position].get("type").toString()
                    when(type){
                        "5" -> setTimeLayout(holder,position)
                    }
                }
            }

            recyclerView.adapter = myAdapter
            myAdapter.setClick(this@ViewHolder)
            TimeThread(jsonObject).start()
        }
    }

    private fun setTimeLayout(holder: ViewHolder, position: Int) {
        holder.setText(R.id.tv_fastshoptime,dataz.get(position).get("time").toString())
    }

    fun setRecycler3(id: Int,jsonObject: JSONObject,context: Context?){
        var recyclerView : RecyclerView = getView(id) as RecyclerView

        val jsonArray = jsonObject.getJSONArray("list")
        var data : ArrayList<Map<String,Object>> = arrayListOf()
        for (i in 0 until jsonArray.length()){
            val jsonObject1 = jsonArray.getJSONObject(i)
            val origin_price = jsonObject1.getString("origin_price")
            val cover_price = jsonObject1.getString("cover_price")
            val string = "${Constants.BASE_URl_IMAGE}${jsonObject1.getString("figure")}"

            var hashMap : HashMap<String,Object> = hashMapOf()
            hashMap.put("price1",origin_price as Object)
            hashMap.put("price2",cover_price as Object)
            hashMap.put("image",string as Object)
            hashMap.put("type","6" as Object)

            data.add(hashMap)
        }


        if(recyclerView!=null && context!=null){
            var manager : LinearLayoutManager = LinearLayoutManager(context)
            manager.orientation = RecyclerView.HORIZONTAL

            recyclerView.layoutManager = manager

            var myAdapter:MyAdapter = object : MyAdapter(){
                override fun bind(holder: ViewHolder, position: Int) {
                   holder.setImageWithUrl(R.id.iv_image,datas[position].get("image").toString(),context)
                    holder.setText(R.id.tv_pricebefore,datas[position].get("price1").toString())
                    holder.setText(R.id.tv_pricenow,datas[position].get("price2").toString())
                }
            }

            recyclerView.adapter = myAdapter

            myAdapter.refresh(data)
            myAdapter.setClick(this@ViewHolder)
        }
    }

    private fun refresh() {
        Activity().runOnUiThread(object : Runnable{
            override fun run() {
                myAdapter.refresh(dataz)
            }
        })
    }

    private fun setTime(msg: Message) {
        val hashMap = msg.obj as HashMap<String,Object>
        dataz.set(hashMap.get("position") as Int,hashMap)
        handler.sendEmptyMessage(100)
    }


    inner class TimeThread:Thread{

        var jsonObject : JSONObject

        constructor(jsonObject: JSONObject) : super() {
            this.jsonObject = jsonObject
        }


        override fun run() {
            super.run()


            val end_time = jsonObject.getString("end_time")
            val start_time = jsonObject.getString("start_time")

            var time = end_time.toLong() - start_time.toLong()
            val time2 = time
            val hour  = (time/1000/60/60).toInt()
            val minutes = (time/1000/60%60).toInt()
            val seconds = (time/1000%60).toInt()

            var hour2 = "$hour"

            if(hour < 10){
                hour2 = "0$hour"
            }

            var minutes2 = "$minutes"

            if(minutes < 10){
                minutes2 = "0$minutes"
            }

            var seconds2 = "$seconds"

            if(seconds < 10){
                seconds2 = "0$seconds"
            }

            var hashMap2 : HashMap<String,Object> = hashMapOf()

            hashMap2.put("time","$hour2:$minutes2:$seconds2" as Object)
            hashMap2.put("type","5" as Object)

            dataz.add(hashMap2)
            var position : Int = dataz.size-1

            handler.sendEmptyMessage(100)


            while (true){
                try {
                    sleep(1000)

                    var hour  = (time/1000/60/60).toInt()
                    val minutes = (time/1000/60%60).toInt()
                    val seconds = (time/1000%60).toInt()

                    var hour2 = "$hour"

                    if(hour < 10){
                        hour2 = "0$hour"
                    }

                    var minutes2 = "$minutes"

                    if(minutes < 10){
                        minutes2 = "0$minutes"
                    }

                    var seconds2 = "$seconds"

                    if(seconds < 10){
                        seconds2 = "0$seconds"
                    }


                    var hashMap : HashMap<String,Object> = hashMapOf()
                    hashMap.put("time","$hour2:$minutes2:$seconds2" as Object)
                    hashMap.put("type","5" as Object)
                    hashMap.put("position",position as Object)

                    val message = Message.obtain()
                    message.what = 200
                    message.obj = hashMap
                    handler.sendMessage(message)

                    time -= 1000
                    if(time <= 0){
                        time = time2
                    }
                }catch (e :InterruptedException){
                    e.printStackTrace()
                }
            }
        }
    }

    fun setViewPager(id : Int,datas : ArrayList<String>,context: Context){
        var viewPager : ViewPager = getView(id) as ViewPager
        if(viewPager!=null){
            viewPager.pageMargin = 40
            viewPager.setPageTransformer(true,CardTransformer())
            var myAdapter2 : MyAdapter2 = MyAdapter2(datas,context)
            viewPager.adapter = myAdapter2
        }
    }

    override fun OnClick(index: Int) {

    }
}