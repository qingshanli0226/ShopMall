package jni.example.atguigu.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.AllPowerfulHolder

abstract class AllPowerfulAdapter<T,V:AllPowerfulHolder>(
    date:List<T>
):RecyclerView.Adapter<V>() {

    var date:List<T> ?= null

    init {
        this.date = date
    }

    override fun getItemViewType(position: Int): Int {
        return getType(position)
    }

    abstract fun getType(position: Int):Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return getViewHolder(parent, viewType)
    }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int):V

    override fun getItemCount(): Int {
        return date!!.size
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        setOnBind(holder,position)
    }

    abstract fun setOnBind(holder: V, position: Int)
}