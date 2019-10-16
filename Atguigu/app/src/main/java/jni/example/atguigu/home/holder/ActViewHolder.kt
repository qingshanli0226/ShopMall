package jni.example.atguigu.home.holder

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import jni.example.atguigu.home.Bean.ActInfo
import jni.example.atguigu.home.adapter.ActAdapter
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.act_item.view.*

class ActViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(act_info:List<ActInfo>?){
        if(act_info==null){
            return
        }
        var imageList:ArrayList<String> = ArrayList()
        for (i in act_info){
            imageList.add("${Constants.BASE_URl_IMAGE}${i.icon_url}")
        }
        with(itemView){
            act_viewpager.pageMargin = 20
            act_viewpager.offscreenPageLimit = 3
            act_viewpager.adapter  = ActAdapter(imageList,itemView.context)
        }
    }
}