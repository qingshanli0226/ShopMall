package com.bw.dianshang.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.bw.dianshang.*
import com.bw.dianshang.home.activity.GoodsListActivity
import com.bw.dianshang.home.bean.GoodsBean
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import java.text.SimpleDateFormat
import java.util.*


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
            SECKILL -> seckillViewHolder.setData(resultBean[p1].seckill_info)
            RECOMMEND -> recommendViewHolder.setData(resultBean[p1].recommend_info)
            HOT -> hotViewHolder.setData(resultBean[p1].hot_info)
        }
    }

    inner class HotViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(hotInfo: List<HotInfo>){

        }
    }

    inner class RecommendViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        init {

        }
        fun setData(recommendInfo: List<RecommendInfo>){

        }
    }

    var isFirst:Boolean = true
    var tvTime:TextView? = null
    var dt:Int? = null
    var handler = @SuppressLint("HandlerLeak") object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if (msg?.what == 0){
                dt = dt!! - 1000
                var sd:SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
                tvTime?.text = sd.format(Date())

                removeMessages(0)
                sendEmptyMessageDelayed(0,1000)
                if (dt == 0){
                    removeMessages(0)
                }
            }
        }
    }

    inner class SeckillViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        var tvMore:TextView? = null
        var recyclerView:RecyclerView? = null
        var mContext:Context = mContext

        init {
            tvMore = item.findViewById(R.id.tv_more_seckill)
            tvTime = item.findViewById(R.id.tv_time_seckill)
            recyclerView = item.findViewById(R.id.rv_seckill)
        }
        fun setData(seckillInfo: SeckillInfo){
            //设置时间
            if (isFirst){
                dt = (Integer.parseInt(seckillInfo.end_time)) - (Integer.parseInt(seckillInfo.start_time))
                isFirst = false
            }

            //设置RecyclerView
            recyclerView?.layoutManager = LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false)

        }
    }

    inner class ActViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        var actViewPager:ViewPager? = null
        var mContext:Context? = null

        init {
            actViewPager = item.findViewById(R.id.act_viewpager)
            this.mContext = mContext
        }
        fun setData(actInfo: List<ActInfo>){
            actViewPager?.pageMargin = 20
            actViewPager?.offscreenPageLimit = 3
//            var scalelnTransformer:ScalelnTransformer = ScalelnTransformer()
//            actViewPager?.setPageTransformer(true,AlphaPageTransformer(scalelnTransformer))

            actViewPager?.adapter = object : PagerAdapter(){
                override fun isViewFromObject(view: View, objects: Any): Boolean {
                    return view == objects
                }

                override fun getCount(): Int {
                    return actInfo.size
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    var view:ImageView = ImageView(mContext)
                    view.scaleType = ImageView.ScaleType.FIT_XY
                    //绑定数据
                    Glide.with(mContext!!).load(Constants.BASE_URl_IMAGE + actInfo[position].icon_url).into(view)
                    container.addView(view)
                    return view
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//                    super.destroyItem(container, position, `object`)
                    container.removeView(`object` as View?)
                }
            }

            //点击事件
            actViewPager?.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(position: Int) {

                }

                override fun onPageScrolled(position: Int, p1: Float, p2: Int) {

                }

                override fun onPageSelected(position: Int) {
                    Toast.makeText(mContext, "position:$position",Toast.LENGTH_SHORT).show()
                }

            } )

        }
    }

    inner class ChannelViewHolder(item: View, mContext: Context): RecyclerView.ViewHolder(item) {

        var gvChannel:GridView? = null
        var mContext:Context? = null

        init {
            gvChannel = item.findViewById(R.id.gv_channel)
            this.mContext = mContext
        }
        fun setData(channelInfo: List<ChannelInfo>){
//            var channelAdapter:ChannelAdapter = ChannelAdapter(mContext,channelInfo)
            gvChannel?.adapter = ChannelAdapter(mContext,channelInfo)

            //点击事件
            gvChannel?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                if (position <= 8){
                    var intent:Intent = Intent(mContext,GoodsListActivity::class.java)
                    intent.putExtra("position",position)
                    mContext?.startActivity(intent)
                }else{

                }
            }
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
            //如果你想用自己的项目图片加载,那么----->自定义图片加载框架
            var list:MutableList<String> = mutableListOf()
            for (item in bannerInfo){
                list.add(item.image)
            }

            banner?.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            banner?.setBannerAnimation(Transformer.Accordion)
            banner?.setImages(list)
            banner?.setImageLoader(OnLoadImageListener())
            banner?.setDelayTime(3000)
            banner?.start()

            //设置点击事件
            banner?.setOnBannerClickListener { position ->
                if (position - 1 < bannerInfo.size){
                    //                        var option = bannerInfo[position - 1].option
                    var product_id:String = ""
                    var name:String = ""
                    var cover_price = ""

                    when(position - 1){
                        0 -> {
                            product_id = "627"
                            cover_price = "32.00"
                            name = "剑三T恤批发"
                        }
                        1 -> {
                            product_id = "21"
                            cover_price = "8.00"
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针"
                        }
                        else -> {
                            product_id = "1341"
                            cover_price = "50.00"
                            name = "【蓝诺】《天下吾双》 剑网3同人本"
                        }
                    }

                    var image:String = bannerInfo[position - 1].image
                    var goodsBean:GoodsBean = GoodsBean(name,cover_price,image,product_id)

                    var intent:Intent = Intent(mContext,GoodsListActivity::class.java)
                    intent.putExtra("goods_bean",goodsBean)
                    mContext?.startActivity(intent)

                }
            }

        }

    }

    class OnLoadImageListener : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context).load(path).into(imageView)
        }

    }

}