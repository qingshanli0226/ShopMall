package com.example.homework.home.adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.homework.R
import com.example.homework.app.GoodsInfoActivity
import com.example.homework.home.bean.*
import com.example.homework.utils.Constants
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import com.youth.banner.transformer.ScaleInOutTransformer
import kotlinx.android.synthetic.main.act_item.view.*
import kotlinx.android.synthetic.main.banner_viewpager.view.*
import kotlinx.android.synthetic.main.channel_item.view.*
import kotlinx.android.synthetic.main.hot_item.view.*
import kotlinx.android.synthetic.main.recommend_item.view.*
import kotlinx.android.synthetic.main.seckill_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class HomeRecycleAdapter(var mContext: Context, var resultBean: ResultBean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)


    /**
     * 根据位置得到类型-系统调用
     */
    override fun getItemViewType(position: Int): Int {
        when (position) {
            BANNER -> currentType = BANNER
            CHANNEL -> currentType = CHANNEL
            ACT -> currentType = ACT
            SECKILL -> currentType = SECKILL
            RECOMMEND -> currentType = RECOMMEND
            HOT -> currentType = HOT
        }
        println("currentType: ${currentType}")
        return currentType
    }

    //返回总条数, 六种类型
    override fun getItemCount(): Int = 6

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        println("viewType: ${viewType}")
        when (viewType) {
            BANNER -> { //广告
                viewHolder = BannerViewHolder(
                    mLayoutInflater.inflate(R.layout.banner_viewpager, null),
                    mContext
                )
            }
            CHANNEL -> {//频道
                viewHolder = ChannelViewHolder(
                    mLayoutInflater.inflate(R.layout.channel_item, null),
                    mContext
                )
            }
            ACT -> {//活动
                viewHolder = ActViewHolder(
                    mLayoutInflater.inflate(R.layout.act_item, null)
                )
            }
            SECKILL -> {
                viewHolder = SeckillViewHolder(
                    mLayoutInflater.inflate(R.layout.seckill_item, null),
                    mContext
                )
            }
            RECOMMEND -> {
                viewHolder = RecommendViewHolder(
                    mLayoutInflater.inflate(R.layout.recommend_item, null)
                )
            }
            HOT -> {
                viewHolder = HotViewHolder(
                    mLayoutInflater.inflate(R.layout.hot_item, null)
                )
            }
        }

        return viewHolder
    }


    //绑定数据
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //判断项目视图类型
        when (getItemViewType(position)) {
            BANNER -> { //广告
                val bannerViewHolder = holder as BannerViewHolder
                bannerViewHolder.setData(resultBean.result.banner_info)
            }
            CHANNEL -> {//频道
                val channelViewHolder = holder as ChannelViewHolder
                channelViewHolder.setData(resultBean.result.channel_info)
            }
            ACT -> {
                val actViewHolder = holder as ActViewHolder
                actViewHolder.setData(resultBean.result.act_info)
            }
            SECKILL -> {
                val seckillViewHolder = holder as SeckillViewHolder
                seckillViewHolder.setData(resultBean.result.seckill_info)
            }
            RECOMMEND -> {
                (holder as RecommendViewHolder).setData(resultBean.result.recommend_info)
            }
            HOT -> {
                (holder as HotViewHolder).setData(resultBean.result.hot_info)
            }
        }
    }

    //热卖
    inner class HotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(data: List<HotInfo>) {

            itemView.rv_hot.layoutManager = GridLayoutManager(mContext, 2)
            val adapter: HotGridViewAdapter = HotGridViewAdapter(mContext, data)
            itemView.rv_hot.adapter = adapter
            adapter.setOnItemClickListener { adapter, view, position ->
                // 10.1.59
                val hotInfo = data[position]
                val goodsBean =
                    GoodsBean(hotInfo.name, hotInfo.cover_price, hotInfo.figure, hotInfo.product_id)

                val intent = Intent(mContext, GoodsInfoActivity::class.java)
                intent.putExtra(GOODS_BEAN, goodsBean)
                mContext.startActivity(intent)



            }


        }

    }


    //推荐
    inner class RecommendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: List<RecommendInfo>) {
            itemView.rv_recommend.layoutManager = GridLayoutManager(mContext, 3)
            itemView.rv_recommend.adapter = RecommendGridViewAdapter(mContext, data)
        }
    }


    private var isFirst = true
    private var tvTime: TextView? = null
    private var dt: Int = 0
    var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {

            if (msg.what == 0) {
                dt = dt - 1000
                val sd = SimpleDateFormat("HH:mm:ss")
                tvTime!!.setText(sd.format(Date(dt.toLong())))

                this.removeMessages(0)

                this.sendEmptyMessageDelayed(0, 1000)
                if (dt == 0) {
                    this.removeMessages(0)
                }
            }
        }
    }

    //秒杀
    inner class SeckillViewHolder(itemView: View, var mContext: Context) :
        RecyclerView.ViewHolder(itemView) {
        init {
            tvTime = itemView.tv_time_seckill
            val tvMore = itemView.tv_more_seckill
        }

        val recyclerView = itemView.rv_seckill

        fun setData(data: SeckillInfo) {
            if (isFirst) {
                dt = Integer.parseInt(data.end_time) - Integer.parseInt(data.start_time)
                isFirst = false
            }

            //设置RecyclerView
            recyclerView.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = SeckillRecyclerViewAdapter(mContext, data)

            handler.sendEmptyMessageDelayed(0, 1000)

            //点击事件


        }


    }

    //活动
    inner class ActViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val actViewPager: ViewPager = itemView.act_viewpager

        fun setData(data: List<ActInfo>) {
            actViewPager.pageMargin = 20 //页边距
            actViewPager.offscreenPageLimit = 3 //屏幕外页面限制
            //设置页面变形金刚
            actViewPager.setPageTransformer(true, ScaleInOutTransformer())

            actViewPager.adapter = object : PagerAdapter() {
                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view == `object`
                }

                override fun getCount(): Int = data.size

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    var view: ImageView = ImageView(mContext)
                    view.scaleType = ImageView.ScaleType.FIT_XY
                    //绑定数据
                    Glide.with(mContext)
                        .load(Constants.BASE_URl_IMAGE + data.get(position).icon_url)
                        .into(view)
                    container.addView(view)
                    return view
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    super.destroyItem(container, position, `object`)
                    container.removeView(`object` as View)
                }
            }

            //TODO actViewPager
//            actViewPager.addOnPageChangeListener()
        }
    }


    //频道
    inner class ChannelViewHolder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        var gvChannel: GridView = itemView.gv_channel

        fun setData(channel_info: List<ChannelInfo>) {
            gvChannel.adapter = ChannelAdapter(context, channel_info)

            //TODO 频道点击监听
            gvChannel.setOnItemClickListener { parent, view, position, id ->
                if (position <= 8) {

                }
            }
        }
    }

    //广告BannerViewHolder
    inner class BannerViewHolder(itemView: View, val ctx: Context) :
        RecyclerView.ViewHolder(itemView) {
        var banner: Banner = itemView.banner

        fun setData(banner_info: List<BannerInfo>) {
            //设置banner的显示样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)

            var imageUris = mutableListOf<String>()
            //添加图片
            for (info in banner_info) {
                imageUris.add(Constants.BASE_URl_IMAGE + info.image)
            }
            banner.setBannerAnimation(Transformer.Accordion)
            banner.setImages(imageUris)
            banner.setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

                    Glide.with(ctx).load(path).into(imageView!!)

                }
            }).start()


            //TODO 广告点击监听
//            banner.setOnBannerListener { position ->
//                    if (position - 1 < banner_info.size) {
//                        var option = banner_info.get(position - 1).option
//
//                    }
//            }
        }
    }


    companion object {
        val BANNER = 0  //广告
        val CHANNEL = 1 //频道
        val ACT = 2   //活动
        val SECKILL = 3 //秒杀
        val RECOMMEND = 4 //推荐
        val HOT = 5 //热卖
        var currentType = BANNER    //当前类型

        val GOODS_BEAN = "goods_bean"
    }
}