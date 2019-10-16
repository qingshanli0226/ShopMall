package com.example.homework.home.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homework.R
import com.example.homework.home.bean.SeckillInfo
import com.example.homework.home.bean.X
import com.example.homework.utils.Constants

class SeckillRecyclerViewAdapter(var context: Context, data: SeckillInfo) :
    BaseQuickAdapter<X, BaseViewHolder>(
        R.layout.item_seckill, data.list
    ) {
    override fun convert(helper: BaseViewHolder, item: X) {
        helper.setText(R.id.tv_cover_price, "￥" + item.cover_price)
        helper.setText(R.id.tv_origin_price, "￥" + item.origin_price)
        Glide.with(context).load(Constants.BASE_URl_IMAGE + item.figure)
            .into(helper.getView(R.id.iv_figure) as ImageView)

        // ll_root 点击事件
    }


}