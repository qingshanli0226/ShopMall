package jni.example.atguigu.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.SeckillInfo
import jni.example.atguigu.home.Bean.X
import jni.example.atguigu.home.holder.SeckillHolder
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.seckill_rv_item.view.*

class SeckillAdapter(
    list: List<X>
): RecyclerView.Adapter<SeckillAdapter.RecyclerViewHolder>() {
    var list: List<X>? = null
    init {
        this.list = list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.seckill_rv_item,parent,false))
    }

    override fun getItemCount(): Int {
        return if(list== null) 0 else list!!.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        with(holder.itemView){
            Glide.with(holder.itemView.context).load("${Constants.BASE_URl_IMAGE}${list!!.get(position).figure}").into(iv_figure)
            tv_cover_price.text = "${list!!.get(position).cover_price}￥"
            tv_origin_price.text = "${list!!.get(position).origin_price}￥"
        }
    }
    inner class RecyclerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
}