package jni.example.atguigu.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.ResultBean
import jni.example.atguigu.home.holder.*


class HomeAdapter(
    bean: ResultBean?,
    mContext: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bean: ResultBean? = null
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
        var Holder:RecyclerView.ViewHolder? = null

        return when(viewType){
            BANNER -> BannerViewHolder(mLayoutInflater!!.inflate(R.layout.home_banner, parent, false))
            CHANNEL ->ChannelHolder(mLayoutInflater!!.inflate(R.layout.channel_item,parent,false))
            ACT->ActViewHolder(mLayoutInflater!!.inflate(R.layout.act_item,parent,false))
            SECKILL-> SeckillHolder(mLayoutInflater!!.inflate(R.layout.seckill_item,parent,false))
            RECOMMEND-> RecommendHolder(mLayoutInflater!!.inflate(R.layout.recommend_item,parent,false))
            HOT -> HotViewHolder(mLayoutInflater!!.inflate(R.layout.hot_item,null))
            else ->ChannelHolder(mLayoutInflater!!.inflate(R.layout.channel_item,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemViewType(position: Int): Int {
        when(position){
            BANNER -> currentType = BANNER
            CHANNEL -> currentType = CHANNEL
            ACT -> currentType = ACT
            SECKILL -> currentType = SECKILL
            RECOMMEND -> currentType = RECOMMEND
            HOT -> currentType = HOT
        }
        return currentType
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position)==BANNER) {
            val bannerViewHolder = holder as BannerViewHolder
            bannerViewHolder.setDate(bean?.result?.banner_info)
        }else if(getItemViewType(position)==CHANNEL){
            val channelHolder = holder as ChannelHolder
            channelHolder.setDate(bean?.result?.channel_info)
        }else if(getItemViewType(position)==ACT){
            val actViewHolder = holder as ActViewHolder
            actViewHolder.setData(bean?.result?.act_info)
        }
        else if(getItemViewType(position)==SECKILL){
            val seckillHolder = holder as SeckillHolder
            seckillHolder.setData(bean?.result?.seckill_info)
        }else if(getItemViewType(position)==RECOMMEND){
            val recommendHolder = holder as RecommendHolder
            recommendHolder.setData(bean?.result?.recommend_info)
        }else if(getItemViewType(position)==HOT){
            val hotViewHolder = holder as HotViewHolder
            hotViewHolder.setData((bean?.result?.hot_info))
        }
    }
}