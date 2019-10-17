package jni.example.atguigu.home.adapter

import android.content.Context
import android.transition.Transition
import android.widget.GridView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.GoodsBean
import jni.example.atguigu.utils.Constants

class RecycleAdpter(data: List<GoodsBean.Result>) :
    BaseQuickAdapter<GoodsBean.Result, BaseViewHolder>( data) {
    init {
        multiTypeDelegate = object : MultiTypeDelegate<GoodsBean.Result>() {
            override fun getItemType(goodsBean: GoodsBean.Result): Int {
                return goodsBean.type
            }
        }
        multiTypeDelegate.registerItemType(0, R.layout.banner)
            .registerItemType(1, R.layout.channel_item)
    }

    override fun convert(helper: BaseViewHolder, item: GoodsBean.Result) {
        when (helper.itemViewType) {
            0 -> {
                var images:ArrayList<String> = arrayListOf()
               var banner:Banner = helper.getView(R.id.home_banner)
                item.bannerInfo.size
                for (it in 0..item.bannerInfo.size-1){
                    val add = images.add(Constants.BASE_URl_IMAGE + item.bannerInfo.get(it).image)
                    println("hhhjjjj:$add")
                }
                banner.setBannerStyle(BannerConfig.CENTER)
                banner.isAutoPlay(true)
                banner.setImages(images)

                banner.setBannerAnimation(Transformer.Default)
                banner.setImageLoader(object :ImageLoader(){
                    override fun displayImage(
                        context: Context,
                        path: Any,
                        imageView: ImageView
                    ) {
                        Glide.with(context).load(path).into(imageView)
                    }

                })
                banner.start()
            }
            1 -> {
                var banner:GridView =   helper.getView(R.id.gv_channel)
            }
        }
    }
}
