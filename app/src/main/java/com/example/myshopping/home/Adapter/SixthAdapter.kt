package com.example.myshopping.home.Adapter

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.home.bean.UrlBean
import com.example.myshopping.utils.Constants

class SixthAdapter(var context: Context) : BaseAdapter<Any, BaseAdapter.BaseViewHolder>() {

    override fun bindView(viewHolder: BaseViewHolder, dataBean: Any, position: Int) {
        val mySixth = viewHolder as MySixth
        val urlBean = dataBean as UrlBean

        Glide.with(context).load(Constants.BASE_URL_IMAGE + urlBean.name).into(mySixth.img)
        mySixth.show.text = urlBean.elseUrl
        mySixth.money.text = "¥" + urlBean.url
    }

    override fun getLayoutId(type: Int): Int {
        return R.layout.sixthlayout
    }

    override fun getViewHolder(type: Int, rootView: View): BaseViewHolder {
        return MySixth(rootView)
    }

    class MySixth(itemView: View) : BaseViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.iv_img)
        var show = itemView.findViewById<TextView>(R.id.tv_show)
        var money = itemView.findViewById<TextView>(R.id.tv_money)
    }

}