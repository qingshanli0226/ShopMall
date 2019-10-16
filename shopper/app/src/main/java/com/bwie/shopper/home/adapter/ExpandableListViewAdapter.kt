package com.atguigu.shoppingmall.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bwie.shopper.R
import com.bwie.shopper.home.adapter.GoodsListActivity

import java.util.ArrayList


/**
 * Created by Administrator on 2016/10/9.
 */
class ExpandableListViewAdapter(context: GoodsListActivity, group: ArrayList<String>, child: ArrayList<List<String>>) : BaseExpandableListAdapter() {
    private val context: Context
    private val group: List<String>
    private val child: List<List<String>>
    private val goodsListActivity: GoodsListActivity
    private var childP: Int = 0
    private var groupP: Int = 0

    init {
        this.context = context
        this.group = group
        this.child = child
        goodsListActivity = GoodsListActivity()
    }

    override fun getGroupCount(): Int {
        return group.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return child.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return group[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return child[childPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_list_item, null)
            holder = ViewHolder()
            holder.textView = convertView!!.findViewById<View>(R.id.textView) as TextView
            holder.imageView = convertView.findViewById<View>(R.id.imageView) as ImageView
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        holder.textView!!.text = group[groupPosition]
        //  holder.textView.setTextSize(20);
        holder.textView!!.setPadding(0, 10, 0, 10)
        if (isExpanded) {
            holder.imageView!!.setImageResource(R.drawable.filter_list_selected)
        } else {
            holder.imageView!!.setImageResource(R.drawable.filter_list_unselected)
        }
        return convertView

    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_list_item, null)
            holder = ViewHolder()
            holder.textView = convertView!!.findViewById<View>(R.id.textView) as TextView
            holder.childImageView = convertView.findViewById<View>(R.id.childImageView) as ImageView
            holder.ll_child_root = convertView.findViewById<View>(R.id.ll_child_root) as LinearLayout
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        if (groupPosition != 0) {
            holder.textView!!.text = child[groupPosition][childPosition]
        }

        //  Toast.makeText(context, "childP" + childP + " " + groupP, Toast.LENGTH_SHORT).show();
        if (childP == childPosition && groupP == groupPosition) {
            holder.childImageView!!.visibility = View.VISIBLE
            notifyDataSetChanged()
        } else {
            holder.childImageView!!.visibility = View.GONE
            notifyDataSetChanged()
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        childP = childPosition
        groupP = groupPosition
        return true
    }

    internal inner class ViewHolder {
        var textView: TextView? = null
        var imageView: ImageView? = null
        var childImageView: ImageView? = null
        var ll_child_root: LinearLayout? = null
    }
}
