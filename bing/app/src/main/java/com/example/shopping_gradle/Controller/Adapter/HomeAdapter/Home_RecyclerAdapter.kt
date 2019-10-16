package com.example.shopping_gradle.Controller.Adapter.HomeAdapter

import android.content.Context
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.shopping_gradle.Model.Entity.ResultBean
import com.example.shopping_gradle.Model.Utlis.AlphaPageTransformer
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.home_act.view.*
import kotlinx.android.synthetic.main.home_banner.view.*
import kotlinx.android.synthetic.main.home_channel.view.*
import kotlinx.android.synthetic.main.home_recommend.view.*
import kotlinx.android.synthetic.main.homt_seckll.view.*
import java.text.SimpleDateFormat
import java.util.*

class Home_RecyclerAdapter(
    context:Context,
    homeList: ResultBean
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var mHome=homeList

    var mContext=context
    private var mlayoutInfalter:LayoutInflater= LayoutInflater.from(mContext)
    var currentType:Int=
        BANNER

    companion object{
        var BANNER:Int=0
        var CHANNEL:Int=1
        var ACT:Int=2
        var SECK:Int=3
        var RECMMMOND=4
        var HOT:Int=5
    }


    override fun getItemViewType(position: Int): Int {
      when(position){
          BANNER -> currentType= BANNER
          CHANNEL ->currentType= CHANNEL
          ACT-> currentType= ACT
          SECK->currentType= SECK
          RECMMMOND-> currentType= RECMMMOND
          HOT->currentType= HOT
      }
        return currentType
    }

    override fun onCreateViewHolder(p0: ViewGroup, type: Int): RecyclerView.ViewHolder {
        if(type== BANNER){
            return BannerViewHolder(mlayoutInfalter.inflate(R.layout.home_banner,null),mContext)
        }else if(type== CHANNEL){
            return ChannelViewHolder(mlayoutInfalter.inflate(R.layout.home_channel,null))
        }else if(type== ACT){
            return ActViewHolder(mlayoutInfalter.inflate(R.layout.home_act,null))
        }else if(type== SECK){
            return SeckllViewHolder(mlayoutInfalter.inflate(R.layout.homt_seckll,null))
        }else if(type== RECMMMOND){
            return RecommendViewHolder(mlayoutInfalter.inflate(R.layout.home_recommend,null))
        }

        return ChannelViewHolder(mlayoutInfalter.inflate(R.layout.home_channel,null))

    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, postion: Int) {

        if(getItemViewType(postion)== BANNER){
            var bannerViewHolder: BannerViewHolder =p0 as BannerViewHolder
            bannerViewHolder.setData(mHome)
        }else if(getItemViewType(postion)== CHANNEL){
            var channelViewHolder:ChannelViewHolder= p0 as ChannelViewHolder
            channelViewHolder.setData()
        }else if(getItemViewType(postion)== ACT){
            var actViewHolder:ActViewHolder=p0 as ActViewHolder
            actViewHolder.setData()
        }else if(getItemViewType(postion)== SECK){
            var seckViewHolder:SeckllViewHolder=p0 as SeckllViewHolder
            seckViewHolder.setData()
        }else if(getItemViewType(postion)== RECMMMOND){
            var recommandViewHolder:RecommendViewHolder=p0 as RecommendViewHolder
            recommandViewHolder.setData()
        }
    }

    inner class HotViewHolder(item:View):RecyclerView.ViewHolder(item){

    }
    inner class RecommendViewHolder(item:View):RecyclerView.ViewHolder(item){

        var MyGridView=item.Recycler_recommend
         var tvMore=item.tv_more_recommend
        fun setData(){

            val grid:GridLayoutManager= GridLayoutManager(mContext,2)
            MyGridView.layoutManager=grid

            val recyclerAdapter:RecommndAdapter= RecommndAdapter(mContext,mHome.result.recommend_info)
            MyGridView.adapter=recyclerAdapter
        }
    }
    var isFirst:Boolean=true
    var dt:Int?=null
    var tTime:TextView?=null

     var handler=object :android.os.Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            if (msg != null) {
                if(msg.what==0){
                    dt= dt!! -1000;
                    var simple:SimpleDateFormat=SimpleDateFormat("HH:mm:ss")
                    tTime!!.text=simple.format(Date(dt!!.toLong()))
                    removeMessages(0)
                    sendEmptyMessageDelayed(0, 1000)
                    if (dt == 0) {
                        removeMessages(0)
                    }
                }
            }
        }
    }



    inner class SeckllViewHolder(item:View):RecyclerView.ViewHolder(item){

        val SeckRecycler=item.Recycler_seckill
        val tMore=item.tv_more_seckill
        val view=item
        fun setData(){

        tTime= view.tv_time_seckill
            if(isFirst){
                dt=mHome.result.seckill_info.end_time.toInt()-mHome.result.seckill_info.start_time.toInt()
                isFirst=false
            }

            val linear:LinearLayoutManager= LinearLayoutManager(mContext)
            linear.orientation=LinearLayoutManager.HORIZONTAL
            SeckRecycler.layoutManager=linear

            var mAdapter:SeckllAdapter= SeckllAdapter(mContext,mHome.result.seckill_info)
            SeckRecycler.adapter=mAdapter
            handler.sendEmptyMessageDelayed(0,1000)
        }
    }

    inner class ActViewHolder(item:View):RecyclerView.ViewHolder(item){

        var actViewPager=itemView.home_ViewPager

        fun setData() {
            actViewPager.pageMargin = 20
            actViewPager.offscreenPageLimit = 3
            actViewPager.setPageTransformer(true, AlphaPageTransformer())
            actViewPager.adapter=object :PagerAdapter(){

                override fun instantiateItem(container: ViewGroup, position: Int): Any {
                    val view= ImageView(mContext)
                    view.scaleType=ImageView.ScaleType.FIT_XY
                    Glide.with(mContext).load(MyConstants.BASE_URl_IMAGE+mHome.result.act_info.get(position).icon_url).into(view)
                    container.addView(view)
                    return view

                }
                override fun isViewFromObject(p0: View, p1: Any): Boolean {
                    return p0==p1
                }

                override fun getCount(): Int {
                    return mHome.result.act_info.size
                }

                override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                    container.removeView(`object`as View)
                }
            }
        }
    }
    inner class ChannelViewHolder(item:View):RecyclerView.ViewHolder(item){

        var gridView=itemView.Channel_gv
        fun setData(){
            var channelAdapter:ChannelAdapter = ChannelAdapter(mContext,mHome.result.channel_info)
            gridView.adapter=channelAdapter
        }
    }
     inner class BannerViewHolder(item: View, mContext: Context):RecyclerView.ViewHolder(item){
        var mbanner=item.Home_Banner
         fun setData(mHomes: ResultBean) {



             var list= mutableListOf<String>()
             list.add("1")
             list.add("2")
             list.add("3")

             val homes=mHomes
             var imgelist= mutableListOf<String>()
             for (item in 0 until homes.result.banner_info.size){
                 imgelist.add(homes.result.banner_info.get(item).image)
             }
             mbanner.setImages(imgelist)
             mbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
             mbanner.setImageLoader(MyLoad())
             mbanner.setDelayTime(1500)
             mbanner.isAutoPlay(true)
             mbanner.setBannerAnimation(Transformer.Accordion)
             mbanner.setIndicatorGravity(BannerConfig.RIGHT)
             mbanner.setBannerTitles(list)
             mbanner.start()


         }


    }

    inner class MyLoad:ImageLoader(){
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context)
                .load(MyConstants.BASE_URl_IMAGE +path.toString())
                .into(imageView)


        }

    }

}