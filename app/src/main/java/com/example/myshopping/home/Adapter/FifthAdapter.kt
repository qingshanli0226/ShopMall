package com.example.myshopping.home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myshopping.R
import com.example.myshopping.home.bean.HomeBean
import com.example.myshopping.home.bean.UrlBean
import com.example.myshopping.utils.Constants

class FifthAdapter(var context: Context, var list: MutableList<UrlBean>) :
    RecyclerView.Adapter<FifthAdapter.MySecond>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySecond {
        val view = LayoutInflater.from(context).inflate(R.layout.fifthlayout, null)
        return MySecond(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MySecond, position: Int) {
        holder.second_text.text = list[position].elseUrl
        Glide.with(context).load(Constants.BASE_URL_IMAGE + list[position].name)
            .into(holder.second_img)
        holder.second_money.text = list[position].url
    }

    inner class MySecond(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var second_img = itemView.findViewById<ImageView>(R.id.fifth_img)
        var second_text = itemView.findViewById<TextView>(R.id.fifth_text)
        var second_money = itemView.findViewById<TextView>(R.id.fifth_money)
    }
}