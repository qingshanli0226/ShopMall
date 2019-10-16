package com.atguigu.shoppingmall.type.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.type.bean.TagBean

import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by Administrator on 2016/10/2.
 */
class TagGridViewAdapter(private val mContext: Context, private val result: List<TagBean.ResultBean>) : BaseAdapter() {
    private val colors = intArrayOf(Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#4ba5e2"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#f0839a"), Color.parseColor("#f0a420"), Color.parseColor("#4ba5e2"))

    override fun getCount(): Int {
        return result.size
    }

    override fun getItem(position: Int): Any {
        return result[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val holder: ViewHolder
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_tab_gridview, null)
            holder = ViewHolder(convertView)
            convertView!!.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val resultBean = result[position]
        holder.tvTag!!.text = resultBean.name
        holder.tvTag!!.setTextColor(colors[position % colors.size])

        return convertView
    }

    internal class ViewHolder(view: View) {
        var tvTag: TextView? = view.findViewById(R.id.tv_tag)

        init {
            ButterKnife.bind(this, view)
        }
    }
}
