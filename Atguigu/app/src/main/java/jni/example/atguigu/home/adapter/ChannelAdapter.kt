package jni.example.atguigu.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.ChannelInfo
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelAdapter(
    channelInfo: List<ChannelInfo>
) : BaseAdapter() {
    var channelInfo:List<ChannelInfo>? = null
    init {
        this.channelInfo = channelInfo
    }
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        lateinit var views:View
        lateinit var holder:ViewHolder
        if(view==null){
             views = LayoutInflater.from(parent!!.context).inflate(R.layout.item_channel,parent,false)
            holder = ViewHolder(views)
            views.setTag(holder)
        }else{
            views = view
            holder = views.getTag() as ViewHolder
        }
        Glide.with(parent!!.context).load("${Constants.BASE_URl_IMAGE}${channelInfo!!.get(position).image}").into(holder.iv_channer)
        holder.tv_channer.text = channelInfo!!.get(position).channel_name
        return views
    }

    override fun getItem(position: Int): Any {
        return channelInfo!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return channelInfo!!.size
    }

    inner class ViewHolder(itemView: View){
        var iv_channer = itemView.iv_channel
        var tv_channer = itemView.tv_channel
    }
}