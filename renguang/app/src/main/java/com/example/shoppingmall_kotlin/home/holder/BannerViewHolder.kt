package com.example.shoppingmall_kotlin.home.holder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.shoppingmall_kotlin.home.bean.BannerInfo
import com.example.shoppingmall_kotlin.home.utils.Constants
import com.squareup.picasso.Picasso
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.home_banner.view.*

class BannerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    fun setDate(banner_info:List<BannerInfo>?){
        if(banner_info == null){
            Log.d("rg","给我出去")
            return
        }

        var imageList:ArrayList<String> = ArrayList()
        for (i in banner_info){
            imageList.add("${Constants.BASE_URl_IMAGE}${i.image}")
        }

        with(itemView){
            home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            home_banner.setImageLoader(GlideImageLoader())
            home_banner.setImages(imageList)
            home_banner.setDelayTime(2000)
            home_banner.start()
        }
    }

    class GlideImageLoader: ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context).load(path).into(imageView)
        }

    }

}

