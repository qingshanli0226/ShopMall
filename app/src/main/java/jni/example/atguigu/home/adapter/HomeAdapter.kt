package jni.example.atguigu.home.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.ResultBean
import jni.example.atguigu.home.holder.BannerViewHolder
import jni.example.atguigu.home.holder.ChannelHolder
import kotlinx.android.synthetic.main.channel_item.view.*
import kotlinx.android.synthetic.main.home_banner.view.*
import kotlin.collections.ArrayList


class HomeAdapter(
    bean: ResultBean,
    mContext: Context?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var bean: ResultBean
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
        var Holder:RecyclerView.ViewHolder? =null
        if (viewType == BANNER) {
            Holder = BannerViewHolder(mLayoutInflater!!.inflate(R.layout.home_banner, parent, false))
            return Holder
        }
        else if (viewType == CHANNEL) {
            Holder = ChannelHolder(mLayoutInflater!!.inflate(R.layout.channel_item,parent,false))
            return Holder
        }
//        } else if (viewType == ACT) {
//            return new
//            ActViewHolder(
//                mLayoutInflater.inflate(R.layout.act_item, null),
//                mContext
//            );
//        } else if (viewType == SECKILL) {
//            return new
//            SeckillViewHolder(
//                mLayoutInflater.inflate(R.layout.seckill_item, null),
//        }
        return Holder!!
    }

    override fun getItemCount(): Int {
        return 2
    }

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


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BannerViewHolder) {
            bean.result.banner_info
        }else if(holder is ChannelHolder){
            with(holder.itemView){
                gv_channel.adapter
            }
        }
    }
    class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context).load(path).into(imageView)
        }
    }



    companion object{
        val GOODS_BEAN = "goods_bean"
        /**
         * 五种类型
         */
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
    }
}