package jni.example.atguigu.home.holder

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import jni.example.atguigu.home.Bean.BannerInfo
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.home_banner.view.*

class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setDate(banner_info: List<BannerInfo>? ){
        if(banner_info==null){
            Log.d("lhf","给我出去")
            return
        }
        var imageList:ArrayList<String> = ArrayList()
        for (i in banner_info){
            imageList.add("${Constants.BASE_URl_IMAGE}${i.image}")
        }
        with(itemView) {
            home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            home_banner.setImageLoader(GlideImageLoader())
            home_banner.setImages(imageList)
            home_banner.setDelayTime(2000)
            home_banner.start()
        }
    }
    class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context).load(path).into(imageView)
        }
    }
}