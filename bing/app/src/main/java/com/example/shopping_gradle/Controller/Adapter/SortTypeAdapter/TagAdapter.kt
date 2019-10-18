package com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Model.Entity.MyTagBean
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.tag_item.view.*

class TagAdapter constructor(list:MutableList<MyTagBean.ResultBean>):RecyclerView.Adapter<TagAdapter.MyHolder>() {

    var mlist=list

    var colors:Array<Int> = arrayOf(Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"),
        Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"),
        Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"))


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.tag_item, p0, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {
        p0.name.text=mlist.get(p1).name
        p0.name.setTextColor(colors[p1%colors.size])
    }

    inner class MyHolder(item: View):RecyclerView.ViewHolder(item){

        var name=item.tv_tag
    }
}