package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.utils.Constants
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

class MyAdapter2 : BaseAdapter<Any, BaseAdapter.BaseViewHolder>() {

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
            1->{

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        var type = when (position) {
            0 -> FIRST_TYPE
            1-> SECOND_TYPE
            else -> FIRST_TYPE
        }
        return type
    }

    //根据不同的布局类型返回不同的布局文件
    override fun getLayoutId(type: Int): Int {
        var layoutId = when (type) {
            FIRST_TYPE -> R.layout.homelayout1
            SECOND_TYPE -> R.layout.homelayout2
            else -> R.layout.homelayout1
        }
        return layoutId
    }

    //根据不同的布局类型返回不同的viewHolder
    override fun getViewHolder(type: Int, rootView: View): BaseViewHolder {
        var viewHolder = when (type) {
            FIRST_TYPE -> FirstHolder(rootView)
            SECOND_TYPE -> FirstHolder(rootView)
            else -> FirstHolder(rootView)
        }
        return viewHolder
    }

}