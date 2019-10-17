package com.example.shoppingmall_kotlin.home.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.shoppingmall_kotlin.home.adapter.ActAdapter
import com.example.shoppingmall_kotlin.home.bean.ActInfo
import com.example.shoppingmall_kotlin.home.utils.Constants
import kotlinx.android.synthetic.main.act_item.view.*
import kotlinx.android.synthetic.main.act_item.view.act_viewpager
import kotlinx.android.synthetic.main.layout_act_info.view.*

class ActHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    fun setDate(act_info:List<ActInfo>?){
        if (act_info==null){
            return
        }

        var imageList:ArrayList<String> = ArrayList()
        for (i in act_info){
            imageList.add("${Constants.BASE_URl_IMAGE}${i.icon_url}")
        }
        with(itemView){
            act_viewpager.pageMargin = 20
            act_viewpager.offscreenPageLimit = 3
            act_viewpager.adapter  = ActAdapter(imageList,itemView.context)
        }
    }
}
