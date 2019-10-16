package com.example.homework1.Controller.Adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import java.security.AccessController.getContext

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var map : HashMap<Int,View> = hashMapOf()

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
                    println("进入加载")
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

}