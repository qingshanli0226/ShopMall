package jni.example.atguigu.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.AllPowerfulHolder

abstract class AllPowerfulAdapter<T,V:AllPowerfulHolder>:RecyclerView.Adapter<V>() {

    private var dataList:MutableList<T> = mutableListOf()//管理的数据

    //更新数据
    fun updateData(beanList:MutableList<T>) {
        dataList.clear()
        dataList.addAll(beanList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return getType(position)
    }

    open  fun getType(position: Int):Int{
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int):V

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        setOnBind(holder,position)
    }

    abstract fun setOnBind(holder: V, position: Int)
}