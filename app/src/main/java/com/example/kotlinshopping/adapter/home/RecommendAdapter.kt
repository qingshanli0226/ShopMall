package com.example.kotlinshopping.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.bean.RecommendInfo

class RecommendAdapter(var context: Context,var data:List<RecommendInfo>): BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        var viewHolder: ViewHolder? = null
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_recommend_grid_view,null)
            viewHolder = ViewHolder()
            viewHolder.ivRecommend = view.findViewById(R.id.iv_recommend)
            viewHolder.tvName = view.findViewById(R.id.tv_name)
            viewHolder.tvPrice = view.findViewById(R.id.tv_price)
            view.tag = viewHolder
        }else{
            viewHolder = view!!.tag as ViewHolder
        }
        Glide.with(context)
            .load(Constants.BASE_URl_IMAGE+data[position].figure)
            .into(viewHolder.ivRecommend!!)
        viewHolder.tvName!!.text = data[position].name
        viewHolder.tvPrice!!.text = "￥${data[position].cover_price}"
        return view
    }

    override fun getItem(position: Int): Any {
      return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
    class ViewHolder(){

        var ivRecommend: ImageView? = null
        var tvName: TextView? = null
        var tvPrice: TextView? = null

    }
}