package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.home.bean.JsonBean
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MyAdpater(var context: Context, var list: MutableList<HomeBean>) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var imagelist = mutableListOf<String>()





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val homelayout1 = View.inflate(context, R.layout.homelayout1, null)
                return FirstHolder(homelayout1)
            }
            2 -> {
                val homelayout2 = View.inflate(context, R.layout.homelayout2, null)
                return FirstHolder(homelayout2)
            }
            3 -> {
                val homelayout3 = View.inflate(context, R.layout.homelayout3, null)
                return FirstHolder(homelayout3)
            }
            4 -> {
                val homelayout4 = View.inflate(context, R.layout.homelayout4, null)
                return FirstHolder(homelayout4)
            }
            5 -> {
                val homelayout5 = View.inflate(context, R.layout.homelayout5, null)
                return FirstHolder(homelayout5)
            }
            6 -> {
                val homelayout6 = View.inflate(context, R.layout.homelayout6, null)
                return FirstHolder(homelayout6)
            }
        }
        val homelayout1 = View.inflate(context, R.layout.homelayout1, null)
        return FirstHolder(homelayout1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val get = list.get(position)
        when (holder) {
            is FirstHolder -> {
                list.forEach {
                    imagelist.add(it.url!!)
                }
                val holder1 = holder as FirstHolder
                holder1.home_Banner.setBannerStyle(BannerConfig.NUM_INDICATOR)
                    .setBannerAnimation(Transformer.DepthPage)
                    .setDelayTime(1500)
                    .setImages(list)
                    .setImageLoader(object : ImageLoader() {
                        override fun displayImage(
                            context: Context?,
                            path: Any?,
                            imageView: ImageView?
                        ) {
                            val toString = path.toString()
                            println("zjw_ : tostring$toString")
                        }

                    })
            }
            is SecondHolder -> {

            }
            is ThirdHolder -> {

            }
            is FourthHolder -> {

            }
            is FifthHolder -> {

            }
            is SixthHolder -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list.get(position).flag
    }


    class FirstHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var home_Banner = itemView.findViewById<Banner>(R.id.home_banner)
    }

    class SecondHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ThirdHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class FourthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class FifthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class SixthHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}