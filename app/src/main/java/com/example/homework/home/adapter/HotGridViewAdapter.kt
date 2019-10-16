package com.example.homework.home.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.homework.R
import com.example.homework.home.bean.HotInfo
import com.example.homework.utils.Constants

class HotGridViewAdapter(mContext: Context, data: List<HotInfo>) :
    BaseQuickAdapter<HotInfo, BaseViewHolder>(R.layout.item_hot_grid_view, data) {
    override fun convert(helper: BaseViewHolder, item: HotInfo) {
        Glide.with(mContext)
            .load(Constants.BASE_URl_IMAGE + item.figure)
            .into(helper.getView(R.id.iv_hot))

        helper.setText(R.id.tv_name, item.name)
        helper.setText(R.id.tv_price, "￥" + item.cover_price)

    }
}