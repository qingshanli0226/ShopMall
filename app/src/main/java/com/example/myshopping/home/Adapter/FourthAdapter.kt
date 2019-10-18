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

class FourthAdapter(var context: Context, var list: MutableList<UrlBean>) :
    RecyclerView.Adapter<FourthAdapter.MySecond>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySecond {
        val view = LayoutInflater.from(context).inflate(R.layout.fourthlayout, null)
        return MySecond(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MySecond, position: Int) {
        holder.text1.text = list[position].url
        Glide.with(context).load(Constants.BASE_URL_IMAGE + list[position].name)
            .into(holder.img1)
        holder.money1.text = list[position].elseUrl
    }

    inner class MySecond(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img1 = itemView.findViewById<ImageView>(R.id.fouth_img1)
        var text1 = itemView.findViewById<TextView>(R.id.fouth_txt1)
        var money1 = itemView.findViewById<TextView>(R.id.fouth_money1)

    }
}