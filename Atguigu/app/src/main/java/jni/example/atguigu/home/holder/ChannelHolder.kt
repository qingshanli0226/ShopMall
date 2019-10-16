package jni.example.atguigu.home.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.home.Bean.ChannelInfo
import jni.example.atguigu.home.adapter.ChannelAdapter
import kotlinx.android.synthetic.main.channel_item.view.*

class ChannelHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setDate(channelInfo: List<ChannelInfo>?){
        if(channelInfo==null){
            return
        }
        with(itemView){
            gv_channel.adapter = ChannelAdapter(channelInfo)
        }
    }
}