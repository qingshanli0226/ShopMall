package com.example.shopping_gradle.Controller.Adapter.HomeAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shopping_gradle.Model.Entity.ChannelInfo
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import com.squareup.picasso.Picasso

class ChannelAdapter(cotext: Context, channel_info: List<ChannelInfo>): BaseAdapter() {

    var chnneList=channel_info
    var mcontex=cotext

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       var myHolder:MyHolder
        var view:View
        if(convertView==null){
            myHolder=MyHolder()

            view=LayoutInflater.from(mcontex).inflate(R.layout.channel_item,parent,false)
            myHolder.ChannelName=view.findViewById(R.id.tv_channel)
            myHolder.ChannelImg=view.findViewById(R.id.img_channel)
            view.tag=myHolder
        }else{
            view=convertView
            myHolder=view.tag as MyHolder
        }
        myHolder.ChannelName.text=chnneList.get(position).channel_name
        Glide.with(mcontex).load(MyConstants.BASE_URl_IMAGE+chnneList.get(position).image).apply(RequestOptions.centerCropTransform()).into(myHolder.ChannelImg)
        return view
    }

    override fun getItem(position: Int): Any {
        return chnneList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if(chnneList==null)
        return 0
        return chnneList.size
    }
    inner class MyHolder{
        lateinit var ChannelImg:ImageView
        lateinit var ChannelName:TextView
    }
}