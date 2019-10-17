package com.example.shoppingmall_kotlin.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shoppingmall_kotlin.R
import com.example.shoppingmall_kotlin.home.bean.BeanHome
import com.example.shoppingmall_kotlin.home.holder.ActHolder
import com.example.shoppingmall_kotlin.home.holder.BannerViewHolder
import com.example.shoppingmall_kotlin.home.holder.ChannelHolder

class HomeAdapter(
    bean: BeanHome?,
    mContext: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bean: BeanHome? = null
    var mContext: Context
    var mLayoutInflater: LayoutInflater? = null

    init {

        this.bean = bean
        this.mContext = mContext!!
        mLayoutInflater = LayoutInflater.from(mContext)
    }

    /**
     * 横幅广告
     */
    val BANNER = 0
    /**
     * 频道
     */
    val CHANNEL = 1
    /**
     * 活动
     */
    val ACT = 2
    /**
     * 秒杀
     */
    val SECKILL = 3
    /**
     * 推荐
     */
    val RECOMMEND = 4
    /**
     * 热卖
     */
    val HOT = 5

    /**
     * 当前类型
     */
    var currentType = BANNER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var Holder:RecyclerView.ViewHolder?=null
            return when(viewType){
                BANNER -> BannerViewHolder(mLayoutInflater!!.inflate(R.layout.home_banner,parent,false))
                ACT->  ActHolder(mLayoutInflater!!.inflate(R.layout.act_item,parent,false))
                else -> ChannelHolder(mLayoutInflater!!.inflate(R.layout.channel_item,parent,false))
            }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("lhf","position:--"+position.toString())
        when(position){
            BANNER -> currentType = BANNER
            CHANNEL -> currentType = CHANNEL
            ACT -> currentType = ACT
            SECKILL -> currentType = SECKILL
            RECOMMEND -> currentType = RECOMMEND
            HOT -> currentType = HOT
        }
        Log.d("lhf","currenType"+currentType.toString())
        return currentType
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        print("rg  holder--${holder is ChannelHolder}")
        if (getItemViewType(position)==BANNER) {
            val bannerViewHolder = holder as BannerViewHolder
            bannerViewHolder.setDate(bean?.result?.banner_info)
        }else if(getItemViewType(position)==CHANNEL){
            val channelHolder = holder as ChannelHolder
            channelHolder.setDate(bean?.result?.channel_info)
        }else if (getItemViewType(position)==ACT){
            val actHolder = holder as ActHolder
            actHolder.setDate(bean?.result?.act_info)
        }
    }
}
