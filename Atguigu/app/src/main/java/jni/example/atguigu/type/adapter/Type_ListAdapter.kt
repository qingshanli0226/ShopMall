package jni.example.atguigu.type.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import jni.example.atguigu.R
import kotlinx.android.synthetic.main.type_list_left_item.view.*

class Type_ListAdapter(
    array: Array<String>,
    context:Context
) : BaseAdapter() {
    var array:Array<String>? = null
    var context = context
    var mySelect:Int = 0
    init {
        this.array = array
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myHolder:MyHolder
        lateinit var views:View
        if(convertView==null){
            views =View.inflate(context, R.layout.type_list_left_item,null)
            myHolder = MyHolder(views)
            views.setTag(myHolder)
        }else{
            views = convertView
            myHolder = views.getTag() as MyHolder
        }
        myHolder.type_left_title.text = array!![position]
        if(mySelect==position){
            views =View.inflate(context, R.layout.type_list_left_item,null)
            views.setBackgroundResource(R.drawable.type_item_background_selector)  //选中项背景
            myHolder.type_left_title.setTextColor(Color.parseColor("#fd3f3f"))
        }else{
            views =View.inflate(context, R.layout.type_list_left_item,null)
            views.setBackgroundResource(R.drawable.bg2)  //其他项背景
            myHolder.type_left_title.setTextColor(Color.parseColor("#323437"))
        }
        return views
    }

    override fun getItem(position: Int): Any {
        return array!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return array!!.size
    }

    class MyHolder(itemView:View){
        var type_left_title:TextView = itemView.type_left_title
    }
}