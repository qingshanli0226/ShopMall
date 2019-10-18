package com.example.myshopping.type.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myshopping.R

class MyTypeListAdapter(var context: Context) : BaseAdapter() {
    private val titles =
        arrayOf("小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品", "办公文具", "数码周边", "游戏专区")
    lateinit var tempView: View
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var typeHolder: TypeHolder? = null
        if (convertView == null) {
            tempView = View.inflate(context, R.layout.typelistlayout, null)
            typeHolder = TypeHolder()
            typeHolder.txt = tempView.findViewById(R.id.type_item_text)
            tempView.setTag(typeHolder)
        } else {
            typeHolder = tempView.getTag() as TypeHolder?
        }

        if (typeHolder != null) {
            typeHolder.txt.text = titles[position]
        }


        return tempView
    }

    override fun getItem(position: Int): Any {
        return titles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return titles.size
    }

    class TypeHolder {
        lateinit var txt: TextView
    }
}