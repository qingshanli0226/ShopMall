package com.example.homework.home.adapter

import android.content.Context
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.homework.R
import com.example.homework.home.bean.ChannelInfo
import com.example.homework.utils.Constants
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelAdapter(val mContext: Context,val channelInfo: List<ChannelInfo>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        var view = convertView
        if (view == null) {
            view = inflate(mContext, R.layout.item_channel, null)
            holder = ViewHolder(view)
            view!!.tag = holder
        } else {
            holder = view!!.tag as ViewHolder
        }

        holder.textView.text = channelInfo[position].channel_name
        Glide.with(mContext).load(Constants.BASE_URl_IMAGE + channelInfo[position].image)
            .into(holder.imageView)

        return view!!
    }

    override fun getItem(position: Int): Any = channelInfo[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = channelInfo.size

    class ViewHolder(view: View) {

        var imageView: ImageView = view.iv_channel
        var textView: TextView = view.tv_channel
    }
}