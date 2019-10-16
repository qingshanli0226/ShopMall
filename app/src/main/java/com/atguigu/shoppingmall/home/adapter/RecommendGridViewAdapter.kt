package com.atguigu.shoppingmall.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.home.bean.ResultBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Administrator on 2016/9/29.
 */
class RecommendGridViewAdapter(private val mContext: Context, private val data: List<ResultBean.Result.RecommendInfo>) : BaseAdapter() {

    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        val recommendInfoBean = data[position]
        Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + recommendInfoBean.figure!!)
                .into(holder.ivRecommend!!)
        holder.tvName!!.text = recommendInfoBean.name
        holder.tvPrice!!.text = "￥" + recommendInfoBean.coverPrice!!
        return convertView
    }

    internal class ViewHolder(view: View) {
        var ivRecommend: ImageView? = view.findViewById(R.id.iv_recommend)
        var tvName: TextView? =  view.findViewById(R.id.tv_name)
        var tvPrice: TextView? = view.findViewById(R.id.tv_price)

        init {
            ButterKnife.bind(this, view)

        }
    }
}
