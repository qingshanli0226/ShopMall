package com.atguigu.shoppingmall.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.home.bean.ResultBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide


/**
 * Created by Administrator on 2016/9/29.
 */
class SeckillRecyclerViewAdapter(private val mContext: Context, private val data: ResultBean.SeckillInfoBean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val list: List<ResultBean.SeckillInfoBean.ListBean>?

    private var onSeckillRecyclerView: OnSeckillRecyclerView? = null

    init {
        list = data.list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_seckill, null))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as MyViewHolder
        myViewHolder.setData(position)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    internal inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivFigure: ImageView
        private val tvCoverPrice: TextView
        private val tvOriginPrice: TextView
        private val ll_root: LinearLayout

        init {
            ivFigure = itemView.findViewById<View>(R.id.iv_figure) as ImageView
            tvCoverPrice = itemView.findViewById<View>(R.id.tv_cover_price) as TextView
            tvOriginPrice = itemView.findViewById<View>(R.id.tv_origin_price) as TextView
            ll_root = itemView.findViewById<View>(R.id.ll_root) as LinearLayout
        }

        fun setData(position: Int) {
            val listBean = list!![position]
            tvCoverPrice.text = "￥" + listBean.cover_price!!
            tvOriginPrice.text = "￥" + listBean.origin_price!!
            Glide.with(mContext)
                    .load(Constants.BASE_URl_IMAGE + listBean.figure!!)
                    .into(ivFigure)
            ll_root.setOnClickListener {
                //  Toast.makeText(mContext, "position" + position, Toast.LENGTH_SHORT).show();
                if (onSeckillRecyclerView != null) {
                    onSeckillRecyclerView!!.onClick(position)
                }
            }
        }
    }

    interface OnSeckillRecyclerView {
        fun onClick(position: Int)
    }

    fun setOnSeckillRecyclerView(onSeckillRecyclerView:OnSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView
    }
}
