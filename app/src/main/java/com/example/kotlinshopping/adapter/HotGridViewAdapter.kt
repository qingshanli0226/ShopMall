package com.example.kotlinshopping.adapter

import android.annotation.SuppressLint
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
import com.example.kotlinshopping.bean.HotInfo

/**
 * Created by Administrator on 2016/10/2.
 */
class HotGridViewAdapter(
    private val mContext: Context,
    private val data: List<HotInfo>
) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
           var holder: ViewHolder? = null
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_grid_view, null)
            holder = ViewHolder()
            holder.ivHot = view.findViewById(R.id.iv_hot)
            holder.tvName = view.findViewById(R.id.tv_name)
            holder.tvPrice = view.findViewById(R.id.tv_price)

            view!!.tag = holder
        } else {
            holder = view!!.tag as ViewHolder
            val hotInfoBean = data[position]
        Glide.with(mContext)
            .load(Constants.BASE_URl_IMAGE + hotInfoBean.figure)
            .into(holder.ivHot!!)
        holder.tvName!!.text = hotInfoBean.name
        holder.tvPrice!!.text = "￥" + hotInfoBean.cover_price
        return convertView
        }

        return view
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    internal class ViewHolder() {
        var ivHot: ImageView? = null
        var tvName: TextView? = null
        var tvPrice: TextView? = null


    }
}
