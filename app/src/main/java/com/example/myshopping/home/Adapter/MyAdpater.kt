package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.home.bean.JsonBean
import com.example.myshopping.utils.Constants
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MyAdpater(context: Context, list: MutableList<HomeBean>) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var ctx = context
    var mlist = list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val homelayout1 = LayoutInflater.from(ctx).inflate(R.layout.homelayout1, null)
                return FirstHolder(homelayout1)
            }
            2 -> {
                val homelayout2 = LayoutInflater.from(ctx).inflate(R.layout.homelayout2, null)
                return SecondHolder(homelayout2)
            }
            3 -> {
                val homelayout3 = LayoutInflater.from(ctx).inflate(R.layout.homelayout3, null)
                return ThirdHolder(homelayout3)
            }
            4 -> {
                val homelayout4 = LayoutInflater.from(ctx).inflate(R.layout.homelayout4, null)
                return FourthHolder(homelayout4)
            }
            5 -> {
                val homelayout5 = LayoutInflater.from(ctx).inflate(R.layout.homelayout5, null)
                return FifthHolder(homelayout5)
            }
            6 -> {
                val homelayout6 = LayoutInflater.from(ctx).inflate(R.layout.homelayout6, null)
                return SixthHolder(homelayout6)
            }
        }
        val homelayout1 = LayoutInflater.from(ctx).inflate(R.layout.homelayout1, null)
        return FirstHolder(homelayout1)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FirstHolder -> {
                holder.setData()
            }
            is SecondHolder -> {
                holder.SecondData()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mlist.get(position).flag
    }


    inner class FirstHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var home_Banner = itemView.findViewById<Banner>(R.id.home_banner)
        fun setData() {
            var imagelist = mutableListOf<String>()
            for (item in 0..mlist.size - 1) {
                val url = mlist.get(item).url
                imagelist.add(url!!)
            }
            home_Banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(1500)
                .setImages(imagelist)
                .setImageLoader(object : ImageLoader() {
                    override fun displayImage(
                        context: Context?,
                        path: Any?,
                        imageView: ImageView?
                    ) {
                        Glide.with(ctx).load(Constants.BASE_URL_IMAGE + path).into(imageView!!)
                    }

                })
                .start()
        }
    }

    inner class SecondHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var home_rlv2 = itemView.findViewById<RecyclerView>(R.id.home_rlv2)
        fun SecondData() {
            val secondAdapter = SecondAdapter(ctx, mlist)
            home_rlv2.layoutManager = GridLayoutManager(ctx, 5)
            home_rlv2.adapter = secondAdapter
        }
    }

    inner class ThirdHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class FourthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class FifthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class SixthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}