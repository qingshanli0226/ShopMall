package com.example.shopping_gradle.Controller.Adapter.HomeAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shopping_gradle.Model.Entity.HotInfo
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.hot_item.view.*

class HotAdapter(ctx: Context, mlist: List<HotInfo>) : RecyclerView.Adapter<HotAdapter.MyHolder>() {

    var mContx = ctx
    var mlists = mlist
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.hot_item, p0, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {

        return mlists.size
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {

        holder.name.text = mlists.get(p1).name
        holder.price.text = mlists.get(p1).cover_price
        Glide.with(mContx).load(MyConstants.BASE_URl_IMAGE + mlists.get(p1).figure).into(holder.img)

    }


    inner class MyHolder(item: View) : RecyclerView.ViewHolder(item) {

        var img = item.hot_itemImg
        var name = item.hot_itemName
        var price = item.hot_itemPrice
    }
}