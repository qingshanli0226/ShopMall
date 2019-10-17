package com.example.homework1.Controller.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.R
import java.util.zip.Inflater

abstract class MyAdapter : RecyclerView.Adapter<ViewHolder>(){


    var datas : ArrayList<Map<String,Object>> = arrayListOf()


    fun refresh(datas : ArrayList<Map<String,Object>>){
        this.datas.clear()
        this.datas.addAll(datas)
        println("size2: ${this.datas.size}")
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        when(viewType){
            0 -> {
                var view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_banner, parent, false)
                return ViewHolder(view)
            }
            1 -> {
                var view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler, parent, false)
                return ViewHolder(view)
            }
            2 -> {
                var view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_channelinfo, parent, false)
                return ViewHolder(view)
            }
            3 -> {
                var view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_actinfo, parent, false)
                return ViewHolder(view)
            }
        }
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_channelinfo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.itemView.setOnClickListener {
           onItemClick.OnClick(position)
       }

        bind(holder,position)
    }

    override fun getItemViewType(position: Int): Int {
        val map = datas[position]
        when(map.get("type").toString()){
            "0" -> return 0
            "1" -> return 1
            "2" -> return 2
            "3" -> return 3
            else -> return -1
        }
    }

    abstract fun bind(holder: ViewHolder,position: Int)

    private lateinit var onItemClick : OnItemClick

    fun setClick(onItemClick: OnItemClick){
        this.onItemClick = onItemClick
    }

    interface OnItemClick{
        fun OnClick(index : Int)
    }
}