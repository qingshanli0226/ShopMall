package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<T : Any?> (var ctx: Context, var list: MutableList<T>, var layoutId: Int) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(ctx).inflate(layoutId, parent, false)

        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    }

    override fun getItemViewType(position: Int): Int {

        return super.getItemViewType(position)
    }


}