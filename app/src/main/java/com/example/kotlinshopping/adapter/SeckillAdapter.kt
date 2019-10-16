package com.example.kotlinshopping.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.bean.SeckillInfo
import kotlinx.android.synthetic.main.item_seckill.view.*

class SeckillAdapter(var context: Context,var seckillInfo: SeckillInfo): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_seckill,null))
    }

    override fun getItemCount(): Int {
       return seckillInfo.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var myViewHolder:MyViewHolder = holder as MyViewHolder
        myViewHolder.setData(position)
    }

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var figure = itemView.iv_figure
        var price = itemView.tv_cover_price
        var origin_price = itemView.tv_origin_price
        var root = itemView.ll_root

        @SuppressLint("SetTextI18n")
        fun setData(position: Int){

            price.text = "￥+${seckillInfo.list[position].cover_price}"
            origin_price.text = "￥+${seckillInfo.list[position].origin_price}"
            Glide.with(context)
                .load(Constants.BASE_URl_IMAGE+seckillInfo.list[position].figure)
                .into(figure)
        }
    }

}