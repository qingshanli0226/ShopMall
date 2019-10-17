package com.bw.dianshang.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.bw.dianshang.*
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import java.nio.channels.Channel


class HomeRecyclerAdapter(mContext: Context,resultBean: MutableList<Result>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val GOODS_BEAN:String = "goods_bean"

    //上下文 mContext
    var mContext:Context = mContext
    //数据Bean对象 resultBean
    var bean:Bean? = null
    var resultBean: MutableList<Result> = resultBean

    //五钟类型
    //横幅广告
    var BANNER:Int = 0
    //频道
    var CHANNEL:Int = 1
    //活动
    var ACT:Int = 2
    //秒杀
    var SECKILL:Int = 3
    //推荐
    var RECOMMEND:Int = 4
    //热卖
    var HOT:Int = 5

    //当前类型
    var currenType:Int = BANNER
    var mLayoutInflater:LayoutInflater = LayoutInflater.from(mContext)

    /**
     * 根据位置得到类型-系统调用
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        when(position){
            BANNER -> currenType = BANNER
            CHANNEL -> currenType = CHANNEL
            ACT -> currenType = ACT
            SECKILL -> currenType = SECKILL
            RECOMMEND -> currenType = RECOMMEND
            HOT -> currenType = HOT
        }
        return currenType
    }

    /**
     * 返回总条数，共六种类型
     * @return
     */
    override fun getItemCount(): Int {
        return 6
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        var bannerViewHolder: BannerViewHolder = BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, p0, false), mContext, resultBean)
        var channelViewHolder: ChannelViewHolder = ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, p0, false), mContext)
        var actViewHolder: ActViewHolder = ActViewHolder(mLayoutInflater.inflate(R.layout.act_item, p0, false), mContext)
        var seckillViewHolder: SeckillViewHolder = SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, p0, false), mContext)
        var recommendViewHolder: RecommendViewHolder = RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, p0, false), mContext)
        var hotViewHolder: HotViewHolder = HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, p0, false), mContext)

        var recyclerView:RecyclerView.ViewHolder = bannerViewHolder
        when(p1){
            BANNER -> recyclerView = bannerViewHolder
            CHANNEL -> recyclerView = channelViewHolder
            ACT -> recyclerView = actViewHolder
            SECKILL -> recyclerView = seckillViewHolder
            RECOMMEND -> recyclerView = recommendViewHolder
            HOT -> recyclerView = hotViewHolder
        }

        return recyclerView
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        var bannerViewHolder:BannerViewHolder = p0 as BannerViewHolder
        var channelViewHolder:ChannelViewHolder = p0 as ChannelViewHolder
        var actViewHolder:ActViewHolder = p0 as ActViewHolder
        var seckillViewHolder:SeckillViewHolder = p0 as SeckillViewHolder
        var recommendViewHolder:RecommendViewHolder = p0 as RecommendViewHolder
        var hotViewHolder:HotViewHolder = p0 as HotViewHolder
        when(getItemViewType(p1)){
            BANNER -> bannerViewHolder.setData(resultBean[p1].banner_info)
            CHANNEL -> channelViewHolder.setData(resultBean[p1].channel_info)
            ACT -> actViewHolder.setData(resultBean[p1].act_info)
            SECKILL -> seckillViewHolder.setData(resultBean[p1].seckill_info.list)
            RECOMMEND -> recommendViewHolder.setData(resultBean[p1].recommend_info)
            HOT -> hotViewHolder.setData(resultBean[p1].hot_info)
        }
    }

    inner class HotViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(bannerInfo: List<HotInfo>){

        }
    }

    inner class RecommendViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(bannerInfo: List<RecommendInfo>){

        }
    }

    inner class SeckillViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(seckillInfo: List<X>){

        }
    }

    inner class ActViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(bannerInfo: List<ActInfo>){

        }
    }

    inner class ChannelViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(bannerInfo: List<ChannelInfo>){

        }
    }

    inner class BannerViewHolder(item: View, mContext: Context, resultBean: MutableList<Result>): RecyclerView.ViewHolder(item) {
        var banner:Banner? =null
        var mContext:Context? = null
        var resultBean:MutableList<Result>? = null
        init {
            banner = item.findViewById(R.id.banner)
            this.mContext = mContext
            this.resultBean = resultBean
        }

        fun setData(bannerInfo: List<BannerInfo>){
            banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            //如果你想用自己的项目图片加载,那么
        }

    }

}