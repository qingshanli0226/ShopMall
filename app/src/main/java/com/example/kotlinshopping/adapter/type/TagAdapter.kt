package com.example.kotlinshopping.adapter.type

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kotlinshopping.R
import com.example.kotlinshopping.bean.TagResult

class TagAdapter(var context: Context,var datas:List<TagResult>): BaseAdapter() {
    private val colors = intArrayOf(
        Color.parseColor("#f0a420"),
        Color.parseColor("#4ba5e2"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#4ba5e2"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#f0a420"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#f0a420"),
        Color.parseColor("#4ba5e2")
    )
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder
        var view = convertView
        if (view == null){

            viewHolder = ViewHolder()
        view = LayoutInflater.from(context).inflate(R.layout.item_tab_gridview,parent,false)
        viewHolder.textView = view.findViewById(R.id.tv_tag)
        view.tag = viewHolder
        }else{
            viewHolder = view.tag as ViewHolder
        }
            viewHolder.textView!!.text = datas[position].name
            viewHolder.textView!!.setTextColor(colors[position % colors.size])
        return view!!
    }

    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas.size
    }
    class ViewHolder{
        var textView:TextView? = null
    }
}