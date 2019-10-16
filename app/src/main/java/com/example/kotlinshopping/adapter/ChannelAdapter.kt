package com.example.kotlinshopping.adapter

import android.content.Context
import android.hardware.camera2.params.OisSample
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.bean.ChannelInfo
import java.util.concurrent.RecursiveTask
//频道的适配器
class ChannelAdapter(var context: Context,var list:List<ChannelInfo>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder:ViewHolder? = null
        var view = convertView

        if (convertView == null)
        {
            view =  LayoutInflater.from(context).inflate(R.layout.item_channel,null)
            viewHolder = ViewHolder()
            viewHolder.textView = view.findViewById(R.id.tv_channel)
            viewHolder.imageView = view.findViewById(R.id.iv_channel)
            view.tag = viewHolder
        }else{
            viewHolder = view!!.tag as ViewHolder?
        }
        viewHolder!!.textView!!.text = list[position].channel_name
        Glide.with(context)
            .load(Constants.BASE_URl_IMAGE+list[position].image)
            .into(viewHolder!!.imageView!!)

        return view
    }

    override fun getItem(position: Int): Any {
       return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    class ViewHolder{
         var imageView:ImageView? = null
        var textView:TextView? = null
    }
}