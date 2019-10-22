package com.bw.dianshang.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bw.dianshang.R
import com.bw.dianshang.home.bean.ResultBean
import com.viewpagerindicator.TitlePageIndicator

class SeckillRecyclerViewAdapter(mContext:Context,data:ResultBean.SeckillInfoBean,list:MutableList<ResultBean.SeckillInfoBean.ListBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mContext:Context = mContext
    var data:ResultBean.SeckillInfoBean = data
    var list:MutableList<ResultBean.SeckillInfoBean.ListBean> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_seckill, null)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val myViewHolder = p0 as MyViewHolder
        myViewHolder.setData(p1,list)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var ll_root:LinearLayout? = null
        var iv_figure:ImageView? = null
        var tv_cover_price:TextView? = null
        var tv_origin_price:TextView? = null

        init {
            ll_root = view.findViewById(R.id.ll_root)
            iv_figure = view.findViewById(R.id.iv_figure)
            tv_cover_price = view.findViewById(R.id.tv_cover_price)
            tv_origin_price = view.findViewById(R.id.tv_origin_price)
        }

        fun setData(position: Int,list: MutableList<ResultBean.SeckillInfoBean.ListBean>){
            var listBean:ResultBean.SeckillInfoBean.ListBean = list[position]
        }

    }

}