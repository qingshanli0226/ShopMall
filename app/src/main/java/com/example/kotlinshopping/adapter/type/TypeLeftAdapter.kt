package com.example.kotlinshopping.adapter.type

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.kotlinshopping.R

class TypeLeftAdapter(var context: Context):BaseAdapter() {
    private val titles:List<String> = listOf("小裙子","上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区")
    private var mSelect = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder
        var view:View? = convertView
        if (view == null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.item_type,parent,false)
            viewHolder = ViewHolder()
            viewHolder.textView = view.findViewById(R.id.tv_title)
            view!!.tag = viewHolder
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.textView!!.text = titles[position]
        if (mSelect==position)
        {
        view.setBackgroundColor(Color.WHITE)
            viewHolder.textView!!.setTextColor(Color.parseColor("#fd3f3f"))
        }else{
            view.setBackgroundColor(Color.parseColor("#EEEEEE"))
            viewHolder.textView!!.setTextColor(Color.parseColor("#232437"))
        }
        return view
    }

    class ViewHolder{ var textView:TextView? = null}

    override fun getItem(position: Int): Any {
       return titles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return titles.size
    }

    fun selectPositon(position: Int){
        if (position != mSelect)
        {
            mSelect = position
            notifyDataSetChanged()
        }
    }
}