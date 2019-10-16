package com.example.kotlinshopping.adapter

import android.content.Context
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.bean.*
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.act_item.view.*
import kotlinx.android.synthetic.main.channel_item.view.*
import kotlinx.android.synthetic.main.home_banner.view.*
import kotlinx.android.synthetic.main.seckill_item.view.*
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeRecyclerAdapter(var context: Context, var resultBean: Result) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mlayoutInflater: LayoutInflater
    //六种类型 六种布局
    //广告
    val BANNER = 0
    //频道
    val CHANNEL = 1
    //活动
    val ACT = 2
    //秒杀
    val SECKILL = 3
    //推荐
    val RECOMMEND = 4
    //热卖
    val HOT = 5

    var currentType = BANNER

    var isFirst = true

    var tvTime:TextView? = null

    var dt:Int = 0
    private val handler = object : Handler() {
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

    init {
        mlayoutInflater = LayoutInflater.from(context)
    }

    //获取多布局类型
    override fun getItemViewType(position: Int): Int {
        when (position) {
            BANNER -> currentType = BANNER
            CHANNEL -> currentType = CHANNEL
            ACT -> currentType = ACT
            SECKILL -> currentType = SECKILL
            RECOMMEND -> currentType = RECOMMEND
            HOT -> currentType = HOT
        }
        return currentType
    }

    //创建viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var v: RecyclerView.ViewHolder
        when (viewType) {
            BANNER -> return BannerViewHolder(
                mlayoutInflater.inflate(R.layout.home_banner, null),
                context,
                resultBean
            )
            CHANNEL -> return ChannelViewHolder(
                mlayoutInflater.inflate(
                    R.layout.channel_item,
                    null
                ), context, resultBean.channel_info
            )
            ACT -> return ActViewHolder(
                mlayoutInflater.inflate(R.layout.act_item, null),
                context,
                resultBean.act_info
            )
            SECKILL -> return SeckillViewHolder(
                mlayoutInflater.inflate(R.layout.seckill_item,null), context,resultBean.seckill_info)
        }
        return v
    }

    //获取类型数量
    override fun getItemCount(): Int {
        return 4
    }

    //绑定viewholder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BANNER -> {
                val bannerViewHolder: BannerViewHolder = holder as BannerViewHolder
                bannerViewHolder.setBannerData()
            }
            CHANNEL -> {
                val channelViewHolder: ChannelViewHolder = holder as ChannelViewHolder
                channelViewHolder.setData()
            }
            ACT -> {
                val actViewHolder: ActViewHolder = holder as ActViewHolder
                actViewHolder.setData()
            }
            SECKILL ->{
                val seckillViewHolder:SeckillViewHolder = holder as SeckillViewHolder
                seckillViewHolder.setData()
            }
        }
    }

    //TODO Banner ViewHolder
    class BannerViewHolder(itemView: View, var context: Context, var resultBean: Result) :
        RecyclerView.ViewHolder(itemView) {
        var banner: Banner

        init {
            banner = itemView.mBanner
        }

        fun setBannerData() {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)

            var url = ArrayList<String>()

            for (i in 0..resultBean.banner_info.size - 1) {
                url.add(Constants.BASE_URl_IMAGE + resultBean.banner_info[i].image)
            }
            banner.setImageLoader(object : ImageLoader() {
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    Glide.with(context!!).load(path).into(imageView!!)
                }
            })
            banner.setBannerAnimation(Transformer.Default)
            banner.setImages(url)
            banner.start()
        }
    }

    //TODO CHANNEL 频道ViewHolder
    class ChannelViewHolder(itemView: View, var context: Context, var list: List<ChannelInfo>) :
        RecyclerView.ViewHolder(itemView) {
        var gridView: GridView = itemView.gv_channel

        fun setData() {
            gridView.adapter = ChannelAdapter(context, list)
        }
    }

    //TODO ACT 活动ViewHolder
    class ActViewHolder(itemView: View, var context: Context, var list: List<ActInfo>) :
        RecyclerView.ViewHolder(itemView) {
        var viewPager: ViewPager = itemView.act_viewpager
        fun setData() {
            viewPager.pageMargin = 20
            viewPager.offscreenPageLimit = 3
            //viewPager.setPageTransformer(true, AlphaPageTransformer(ScaleInTransformer()))
            viewPager.adapter = object : PagerAdapter() {
                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view == `object`
                }

                override fun getCount(): Int {
                    return list.size
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    var imageView = ImageView(context)
                    imageView.scaleType = ImageView.ScaleType.FIT_XY
                    Glide.with(context)
                        .load(Constants.BASE_URl_IMAGE + list.get(position).icon_url)
                        .into(imageView)
                    container.addView(imageView)
                    return imageView
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View?)
                }
            }
        }
    }

    //SECKILL 秒杀ViewHolder
 internal inner  class SeckillViewHolder(itemView: View,var context: Context,var seckillInfo: SeckillInfo) : RecyclerView.ViewHolder(itemView){
        var recyclerView:RecyclerView

        var tvMore:TextView
        init {
            tvTime = itemView.tv_time_seckill
            tvMore = itemView.tv_more_seckill
            recyclerView = itemView.rv_seckill
        }
        fun setData(){
            //设置布局上的时间
            if (isFirst){
                dt = seckillInfo.end_time.toInt() - seckillInfo.start_time.toInt()
                isFirst = false
            }

            recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            recyclerView.adapter = SeckillAdapter(context,seckillInfo)

            handler.sendEmptyMessageDelayed(0,1000)

        }
    }

}