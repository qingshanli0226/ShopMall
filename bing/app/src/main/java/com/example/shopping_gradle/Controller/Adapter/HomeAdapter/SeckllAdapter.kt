package com.example.shopping_gradle.Controller.Adapter.HomeAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shopping_gradle.Model.Entity.SeckillInfo
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.seckll_item.view.*

class SeckllAdapter(mContext: Context, mHome: SeckillInfo): RecyclerView.Adapter<SeckllAdapter.MyHolder>() {

    val ctx=mContext;
    var mlist=mHome
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view:View=LayoutInflater.from(p0.context).inflate(R.layout.seckll_item,p0,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return mlist.list.size
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {
        holder.tvPrice.text="￥"+mlist.list.get(p1).cover_price
        holder.tvOriginPrice.text="￥"+mlist.list.get(p1).origin_price
        Glide.with(ctx).load(MyConstants.BASE_URl_IMAGE+mlist.list.get(p1).figure).into(holder.image)
    }


    inner class MyHolder constructor(item: View):RecyclerView.ViewHolder(item){

        var image=item.iv_figure
        var  tvPrice=item.tv_cover_price
        var tvOriginPrice=item.tv_origin_price

    }

}