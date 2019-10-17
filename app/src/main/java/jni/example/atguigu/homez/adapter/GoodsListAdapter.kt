package com.atguigu.shoppingmall.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.home.activity.GoodsListActivity
import com.atguigu.shoppingmall.home.bean.TypeListBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide

/**
 * Created by Administrator on 2016/10/12.
 */
class GoodsListAdapter(mContext: GoodsListActivity, private val page_data: List<TypeListBean.ResultBean.PageDataBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mContext: Context


    private var onItemClickListener: OnItemClickListener? = null

    init {
        this.mContext = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(View.inflate(mContext, R.layout.item_goods_list_adapter, null))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.setData(page_data[position])

    }

    override fun getItemCount(): Int {
        return page_data.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iv_hot: ImageView
        private val tv_name: TextView
        private val tv_price: TextView
        private var data: TypeListBean.ResultBean.PageDataBean? = null

        init {
            iv_hot = itemView.findViewById<View>(R.id.iv_hot) as ImageView
            tv_name = itemView.findViewById<View>(R.id.tv_name) as TextView
            tv_price = itemView.findViewById<View>(R.id.tv_price) as TextView

            itemView.setOnClickListener {
                if (onItemClickListener != null) {
                    onItemClickListener!!.setOnItemClickListener(data)
                }
            }
        }

        fun setData(data: TypeListBean.ResultBean.PageDataBean) {
            Glide.with(mContext).load(Constants.BASE_URl_IMAGE + data.figure!!).into(iv_hot)
            tv_name.text = data.name
            tv_price.text = "￥" + data.cover_price!!
            this.data = data

        }
    }

    interface OnItemClickListener {
        fun setOnItemClickListener(data: TypeListBean.ResultBean.PageDataBean?)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}
