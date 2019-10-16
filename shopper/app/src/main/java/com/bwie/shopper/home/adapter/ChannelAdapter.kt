package com.atguigu.shoppingmall.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.atguigu.shoppingmall.home.bean.ResultBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide

import com.bwie.shopper.R

/**
 * Created by Administrator on 2016/9/28.
 */
class ChannelAdapter( val mContext: Context,  val channel_info: List<ResultBean.ChannelInfoBean>?) : BaseAdapter() {


    override fun getCount(): Int {
        return channel_info!!.size
    }

    override fun getItem(position: Int): Any {
        return channel_info!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holer: ViewHolder
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null)
            holer = ViewHolder(convertView)
            convertView!!.tag = holer
        } else {
            holer = convertView.tag as ViewHolder
        }

        val channelInfoBean = channel_info!![position]
        holer.tvChannel!!.text = channelInfoBean.channel_name
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + channelInfoBean.image!!)
                .into(holer.ivChannel!!)
        return convertView
    }

    internal inner class ViewHolder(view: View) {
        var ivChannel: ImageView? =view.findViewById(R.id.iv_channel)
        var tvChannel: TextView? = view.findViewById(R.id.tv_channel)
    }
}
