package com.example.myshopping.home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, V : BaseAdapter.BaseViewHolder> : RecyclerView.Adapter<V>() {

    private var dataList: MutableList<T> = mutableListOf()

    fun updataData(beanList: MutableList<T>) {
        dataList.clear()
        dataList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    open fun getViewType(position: Int): Int {
        return position
    }

    override fun getItemViewType(position: Int): Int {
        val type = getViewType(position)
        return type
    }

    abstract fun bindView(viewHolder: V, dataBean: T, position: Int)

    override fun onBindViewHolder(holder: V, position: Int) {
        bindView(holder, dataList.get(position), position)
    }

    abstract fun getLayoutId(type: Int): Int
    //让子类来返回一个具体的viewholder
    abstract fun getViewHolder(type: Int, rootView: View): V

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        val layoutId = getLayoutId(viewType)
        val rootView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return getViewHolder(viewType, rootView)
    }

    open class BaseViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}