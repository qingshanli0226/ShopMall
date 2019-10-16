package com.atguigu.shoppingmall.type.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.atguigu.shoppingmall.R


/**
 * Created by Administrator on 2016/10/3.
 */
class TypeLeftAdapter(private val mContext: Context) : BaseAdapter() {
    private var mSelect = 0//选中项
    private val titles = arrayOf("小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区")

    override fun getCount(): Int {
        return titles.size
    }

    override fun getItem(position: Int): Any {
        return titles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_type, null)
            holder = ViewHolder()
            holder.tv_name = convertView!!.findViewById<View>(R.id.tv_title) as TextView

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.tv_name!!.text = titles[position]

        if (mSelect == position) {
            convertView.setBackgroundResource(R.drawable.type_item_background_selector)  //选中项背景
            holder.tv_name.setTextColor(Color.parseColor("#fd3f3f"))
        } else {
            convertView.setBackgroundResource(R.drawable.bg2)  //其他项背景
            holder.tv_name.setTextColor(Color.parseColor("#323437"))
        }
        return convertView
    }

    fun changeSelected(positon: Int) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon
            notifyDataSetChanged()
        }
    }

    internal class ViewHolder {
        lateinit var tv_name: TextView
    }
}
