package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.utils.Constants
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MyAdapter(var context: Context) : BaseAdapter<Any, BaseAdapter.BaseViewHolder>() {

    private val FIRST_TYPE = 100
    private val SECOND_TYPE = 200
    private val THIRD_TYPE = 300
    private val FOURTH_TYPE = 400
    private val FIFTH_TYPE = 500
    private val SIXTH_TYPE = 600

    override fun bindView(viewHolder: BaseViewHolder, dataBean: Any, position: Int) {
        when (position) {
            0 -> {
                val firstHolder = viewHolder as FirstHolder
                val mutableList = dataBean as HomeBean
                if (mutableList.flag == 1) {
                    var imagelist = mutableListOf<String>()
                    val get = mutableList.list
                    get.forEach {
                        imagelist.add(it.url!!)
                    }
                    firstHolder.home_Banner
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setBannerAnimation(Transformer.DepthPage)
                        .setDelayTime(1500)
                        .setImages(imagelist)
                        .setImageLoader(object : ImageLoader() {
                            override fun displayImage(
                                context: Context?,
                                path: Any?,
                                imageView: ImageView?
                            ) {
                                Glide.with(context!!).load(Constants.BASE_URL_IMAGE + path)
                                    .into(imageView!!)
                            }
                        })
                        .start()
                }

            }
            1 -> {
                val secondHolder = viewHolder as SecondHolder
                val homeBean = dataBean as HomeBean
                val mlist = homeBean.list

                if (homeBean.flag == 2) {
                    val secondAdapter = SecondAdapter(context, mlist)
                    secondHolder.home_rlv2.layoutManager = GridLayoutManager(context, 5)
                    secondHolder.home_rlv2.adapter = secondAdapter
                }
            }
            2 -> {
                val thirdHolder = viewHolder as ThirdHolder
                val homeBean = dataBean as HomeBean
                if (homeBean.flag == 3) {
                    val list = homeBean.list
                    var imglist = mutableListOf<String>()
                    list.forEach {
                        imglist.add(it.url!!)
                    }
                    thirdHolder.third_bn.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setImages(imglist)
                        .isAutoPlay(false)
                        .setImageLoader(object : ImageLoader() {
                            override fun displayImage(
                                context: Context?,
                                path: Any?,
                                imageView: ImageView?
                            ) {
                                Glide.with(context!!).load(Constants.BASE_URL_IMAGE + path)
                                    .into(imageView!!)
                            }

                        })
                        .start()
                }

            }
            3 -> {
                val fourthHolder = viewHolder as FourthHolder
                val homeBean = dataBean as HomeBean

                if (homeBean.flag == 4) {
                    val list = homeBean.list
                    val fourthAdapter = FourthAdapter(context, list)
                    val linearLayoutManager = LinearLayoutManager(context)
                    linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    fourthHolder.rlv.layoutManager = linearLayoutManager
                    fourthHolder.rlv.adapter = fourthAdapter
                }
            }
            4 -> {
                val fifthHolder = viewHolder as FifthHolder
                val homeBean = dataBean as HomeBean
                if (homeBean.flag == 5) {
                    val get = homeBean.list
                    val fifthAdapter = FifthAdapter(context, get)
                    fifthHolder.home_rlv5.layoutManager = GridLayoutManager(context, 3)
                    fifthHolder.home_rlv5.adapter = fifthAdapter
                }
            }
            5 -> {
                val sixthHolder = viewHolder as SixthHolder
                val homeBean = dataBean as HomeBean

                if (homeBean.flag == 6) {
                    val sixthAdapter = SixthAdapter(context)
                    sixthAdapter.updataData(homeBean.list as MutableList<Any>)
                    sixthHolder.home_rlv6.layoutManager = GridLayoutManager(context, 2)
                    sixthHolder.home_rlv6.adapter = sixthAdapter
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        var type = when (position) {
            0 -> FIRST_TYPE
            1 -> SECOND_TYPE
            2 -> THIRD_TYPE
            3 -> FIFTH_TYPE
            4 -> FOURTH_TYPE
            5 -> SIXTH_TYPE
            else -> FIRST_TYPE
        }
        return type
    }

    //根据不同的布局类型返回不同的布局文件
    override fun getLayoutId(type: Int): Int {
        var layoutId = when (type) {
            FIRST_TYPE -> R.layout.homelayout1
            SECOND_TYPE -> R.layout.homelayout2
            THIRD_TYPE -> R.layout.homelayout3
            FIFTH_TYPE -> R.layout.homelayout4
            FOURTH_TYPE -> R.layout.homelayout5
            SIXTH_TYPE -> R.layout.homelayout6
            else -> R.layout.homelayout1
        }
        return layoutId
    }

    //根据不同的布局类型返回不同的viewHolder
    override fun getViewHolder(type: Int, rootView: View): BaseAdapter.BaseViewHolder {
        var viewHolder = when (type) {
            FIRST_TYPE -> FirstHolder(rootView)
            SECOND_TYPE -> SecondHolder(rootView)
            THIRD_TYPE -> ThirdHolder(rootView)
            FIFTH_TYPE -> FourthHolder(rootView)
            FOURTH_TYPE -> FifthHolder(rootView)
            SIXTH_TYPE -> SixthHolder(rootView)
            else -> FirstHolder(rootView)
        }
        return viewHolder
    }

    class FirstHolder(itemView: View) : BaseAdapter.BaseViewHolder(itemView) {
        var home_Banner = itemView.findViewById<Banner>(R.id.home_banner)
    }

    class SecondHolder(itemView: View) :BaseAdapter.BaseViewHolder(itemView) {
        var home_rlv2 = itemView.findViewById<RecyclerView>(R.id.home_rlv2)
    }

    class ThirdHolder (itemView: View): BaseAdapter.BaseViewHolder(itemView) {
        var third_bn = itemView.findViewById<Banner>(R.id.third_bn)
    }

    class FourthHolder (itemView: View): BaseAdapter.BaseViewHolder(itemView) {
        var rlv = itemView.findViewById<RecyclerView>(R.id.home_rlv4)
    }

    class FifthHolder (itemView: View): BaseAdapter.BaseViewHolder(itemView) {
        var home_rlv5 = itemView.findViewById<RecyclerView>(R.id.home_rlv5)
    }

    class SixthHolder(itemView: View) : BaseAdapter.BaseViewHolder(itemView) {
        var home_rlv6 = itemView.findViewById<RecyclerView>(R.id.home_rlv6)
    }

}