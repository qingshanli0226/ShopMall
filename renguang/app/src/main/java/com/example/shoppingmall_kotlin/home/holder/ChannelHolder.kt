package com.example.shoppingmall_kotlin.home.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.shoppingmall_kotlin.home.adapter.ChannelAdapter
import com.example.shoppingmall_kotlin.home.bean.ChannelInfo
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
