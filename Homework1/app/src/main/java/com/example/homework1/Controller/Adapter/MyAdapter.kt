package com.example.homework1.Controller.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework1.R
import java.util.zip.Inflater

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>{

    var datas : ArrayList<Map<String,Object>>
    var context : Context

    constructor(datas: ArrayList<Map<String, Object>>, context: Context) {
        this.datas = datas
        this.context = context
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View
        when(viewType){
            0 ->  view = LayoutInflater.from(context).inflate(R.layout.layout_banner,parent,false)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        val get = datas.get(position)
        val type = get.get("type").toString()
        when(type){
            "0" -> return 0
            else -> return 1
        }
    }
}