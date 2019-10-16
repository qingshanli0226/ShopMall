package com.atguigu.shoppingmall.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.atguigu.shoppingmall.app.GoodsInfoActivity
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.home.bean.ResultBean

import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide
import com.bwie.shopper.R
import com.bwie.shopper.home.adapter.GoodsListActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import com.youth.banner.loader.ImageLoaderInterface

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date


class HomeRecycleAdapter(
        /**
         * 上下文
         */
        private val mContext: Context,
        /**
         * 数据Bean对象
         */
        private val resultBean: ResultBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    /**
     * 当前类型
     */
    var currentType = BANNER
    private val mLayoutInflater: LayoutInflater

    private var isFirst = true
    private var tvTime: TextView? = null
    private var dt: Int = 0
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 0) {
                dt = dt - 1000
                val sd = SimpleDateFormat("HH:mm:ss")
                tvTime!!.text = sd.format(Date(dt.toLong()))

                this.removeMessages(0)
                this.sendEmptyMessageDelayed(0, 1000)
                if (dt == 0) {
                    this.removeMessages(0)
                }
            }

        }
    }


    init {
        mLayoutInflater = LayoutInflater.from(mContext)
    }

    /**
     * 根据位置得到类型-系统调用
     * @param position
     * @return
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
        return currentType
    }

    /**
     * 返回总条数，共六种类型
     * @return
     */
    override fun getItemCount(): Int {
        return 6
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            BANNER->return BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext,resultBean!!
            )
            CHANNEL->return ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, null), mContext)
            ACT->return ActViewHolder(mLayoutInflater.inflate(R.layout.act_item, null), mContext)
            SECKILL->return SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, null), mContext)
            RECOMMEND->return RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, null), mContext)
            else->return HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, null), mContext)
        }
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == BANNER) {
            val bannerViewHolder = holder as BannerViewHolder
            bannerViewHolder.setData(resultBean!!.banner_info)
        } else if (getItemViewType(position) == CHANNEL) {
            val channelViewHolder = holder as ChannelViewHolder
            channelViewHolder.setData(resultBean!!.channel_info)
        } else if (getItemViewType(position) == ACT) {
            val actViewHolder = holder as ActViewHolder
            actViewHolder.setData(resultBean!!.act_info)
        } else if (getItemViewType(position) == SECKILL) {
            val seckillViewHolder = holder as SeckillViewHolder
            seckillViewHolder.setData(resultBean!!.seckill_info)
        } else if (getItemViewType(position) == RECOMMEND) {
            val recommendViewHolder = holder as RecommendViewHolder
            recommendViewHolder.setData(resultBean!!.recommend_info)
        } else if (getItemViewType(position) == HOT) {
            val hotViewHolder = holder as HotViewHolder
            hotViewHolder.setData(resultBean!!.hot_info)
        }
    }

    internal inner class HotViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private val tv_more_hot: TextView
        private val gv_hot: GridView

        init {
            tv_more_hot = itemView.findViewById<View>(R.id.tv_more_hot) as TextView
            gv_hot = itemView.findViewById<View>(R.id.gv_hot) as GridView
        }

        fun setData(data: List<ResultBean.HotInfoBean>) {
            val adapter = HotGridViewAdapter(mContext, data)
            gv_hot.adapter = adapter

            //点击事件
            gv_hot.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                // Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                val cover_price = data!![position].cover_price
                val name = data[position].name
                val figure = data[position].figure
                val product_id = data[position].product_id
                val goodsBean = GoodsBean(name, cover_price, figure, product_id)

                val intent = Intent(mContext, GoodsInfoActivity::class.java)
                intent.putExtra(GOODS_BEAN, goodsBean)
                mContext.startActivity(intent)
            }
        }
    }

    internal inner class RecommendViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private val tv_more_recommend: TextView
        private val gv_recommend: GridView

        init {
            tv_more_recommend = itemView.findViewById<View>(R.id.tv_more_recommend) as TextView
            gv_recommend = itemView.findViewById<View>(R.id.gv_recommend) as GridView
        }

        fun setData(data: List<ResultBean.RecommendInfoBean>?) {
            val adapter = data?.let { RecommendGridViewAdapter(mContext, it) }
            gv_recommend.adapter = adapter

            //点击事件
            gv_recommend.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                //Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                val cover_price = data!![position].cover_price
                val name = data[position].name
                val figure = data[position].figure
                val product_id = data[position].product_id
                val goodsBean = GoodsBean(name, cover_price, figure, product_id)
                //
                val intent = Intent(mContext, GoodsInfoActivity::class.java)
                intent.putExtra(GOODS_BEAN, goodsBean)
                mContext.startActivity(intent)
            }
        }
    }

    internal inner class SeckillViewHolder(itemView: View, var mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private val tvMore: TextView
        private val recyclerView: RecyclerView

        init {
            tvTime = itemView.findViewById<View>(R.id.tv_time_seckill) as TextView
            tvMore = itemView.findViewById<View>(R.id.tv_more_seckill) as TextView
            recyclerView = itemView.findViewById<View>(R.id.rv_seckill) as RecyclerView
        }


        fun setData(data: ResultBean.SeckillInfoBean?) {
            //设置时间
            if (isFirst) {
                //                dt = (int) (Integer.parseInt(data.getEnd_time()) - System.currentTimeMillis());
                dt = Integer.parseInt(data!!.end_time!!) - Integer.parseInt(data.start_time!!)
                isFirst = false
            }

            //设置RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            val adapter = SeckillRecyclerViewAdapter(mContext, data!!)
            recyclerView.adapter = adapter


            //倒计时
            handler.sendEmptyMessageDelayed(0, 1000)

            //点击事件
            adapter.setOnSeckillRecyclerView(object :SeckillRecyclerViewAdapter.OnSeckillRecyclerView{
                override fun onClick(position: Int) {
                    val listBean = data.list!![position as Int]
                    val name = listBean.name
                    val cover_price = listBean.cover_price
                    val figure = listBean.figure
                    val product_id = listBean.product_id
                    val goodsBean = GoodsBean(name, cover_price, figure, product_id)
                    //
                    val intent = Intent(mContext, GoodsInfoActivity::class.java)
                    intent.putExtra(GOODS_BEAN, goodsBean)
                    mContext.startActivity(intent)

                    // Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    true
                }
            })

        }
    }

    internal inner class ActViewHolder(itemView: View, var mContext: Context) : RecyclerView.ViewHolder(itemView) {
        var actViewPager: ViewPager

        init {
            actViewPager = itemView.findViewById<View>(R.id.act_viewpager) as ViewPager
        }

        fun setData(data: List<ResultBean.ActInfoBean>?) {
            actViewPager.pageMargin = 20
            actViewPager.offscreenPageLimit = 3
//            actViewPager.setPageTransformer(true, AlphaPageTransformer(ScaleInTransformer()))

            actViewPager.adapter = object : PagerAdapter() {
                override fun getCount(): Int {
                    return data!!.size
                }

                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view === `object`
                }

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val view = ImageView(mContext)
                    view.scaleType = ImageView.ScaleType.FIT_XY
                    //绑定数据
                    Glide.with(mContext)
                            .load(Constants.BASE_URl_IMAGE + data!![position].icon_url!!)
                            .into(view)
                    container.addView(view)
                    return view
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object` as View)
                }
            }

            //点击事件
            actViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    Toast.makeText(mContext, "position:$position", Toast.LENGTH_SHORT).show()
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })
        }
    }

    internal inner class ChannelViewHolder(itemView: View, var mContext: Context) : RecyclerView.ViewHolder(itemView) {
        var gvChannel: GridView

        init {
            gvChannel = itemView.findViewById<View>(R.id.gv_channel) as GridView
        }

        fun setData(channel_info: List<ResultBean.ChannelInfoBean>?) {
            gvChannel.adapter = ChannelAdapter(mContext, channel_info)

            //点击事件
            gvChannel.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                if (position <= 8) {
                    val intent = Intent(mContext, GoodsListActivity::class.java)
                    intent.putExtra("position", position)
                    mContext.startActivity(intent)
                } else {

                }
            }
        }

    }

    internal inner class BannerViewHolder(itemView: View, var mContext: Context, var resultBean: ResultBean) : RecyclerView.ViewHolder(itemView) {
        var banner: Banner

        init {
            banner = itemView.findViewById<View>(R.id.banner) as Banner
        }

        fun setData(banner_info: List<ResultBean.BannerInfoBean>?) {

            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            //如果你想用自己项目的图片加载,那么----->自定义图片加载框架
            val imageUris = ArrayList<String>()
            for (i in 0 until resultBean.banner_info!!.size) {
                imageUris.add(resultBean.banner_info!![i].image.toString())
            }
            banner.setBannerAnimation(Transformer.Accordion)
            banner.setDelayTime(3)
            banner.setImages(imageUris)
            banner.setImageLoader(object :ImageLoader(){
                override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                    Glide.with(mContext)
                        .load(Constants.BASE_URl_IMAGE + path)
                        .into(imageView!!)
                }
            })
            //设置点击事件
            banner.setOnBannerClickListener { position ->
                if (position - 1 < banner_info!!.size) {
                    val option = banner_info[position - 1].option
                    var product_id = ""
                    var name = ""
                    var cover_price = ""
                    if (position - 1 == 0) {
                        product_id = "627"
                        cover_price = "32.00"
                        name = "剑三T恤批发"
                    } else if (position - 1 == 1) {
                        product_id = "21"
                        cover_price = "8.00"
                        name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针"
                    } else {
                        product_id = "1341"
                        cover_price = "50.00"
                        name = "【蓝诺】《天下吾双》 剑网3同人本"
                    }
                    val image = banner_info[position - 1].image
                    val goodsBean = GoodsBean(name, cover_price, image, product_id)

                    val intent = Intent(mContext, GoodsInfoActivity::class.java)
                    intent.putExtra("goods_bean", goodsBean)
                    mContext.startActivity(intent)
                }
            }

        }
    }

    companion object {

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
