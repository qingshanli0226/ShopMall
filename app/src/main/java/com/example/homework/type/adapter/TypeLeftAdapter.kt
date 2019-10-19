package com.example.homework.type.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.homework.R
import org.jetbrains.anko.find

class TypeLeftAdapter(var mContext: Context) : BaseAdapter() {
    private var mSelect = 0
    private var titles =
        arrayOf("小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区")

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder
        var view = convertView
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item_type, null)

            holder = ViewHolder()
            holder.tv_name = view.find(R.id.tv_title)

            view.tag = holder
        } else {
            holder = view!!.tag as ViewHolder
        }
        holder.tv_name.text = titles[position]

        if (mSelect == position) {
            view!!.setBackgroundResource(R.drawable.type_item_background_selector)
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"))
        } else {
            view!!.setBackgroundResource(R.drawable.bg2)
            holder.tv_name.setTextColor(Color.parseColor("#323437"))
        }


        return view!!
    }

    fun changeSelected(position: Int) { //刷新方法
        if (position != mSelect) {
            mSelect = position
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder {
        lateinit var tv_name: TextView
    }


    override fun getItem(position: Int): Any = titles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = titles.size
}