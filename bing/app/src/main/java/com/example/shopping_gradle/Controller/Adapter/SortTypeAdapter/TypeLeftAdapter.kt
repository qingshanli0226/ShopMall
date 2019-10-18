package com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.left_type.view.*

class TypeLeftAdapter:RecyclerView.Adapter<TypeLeftAdapter.MyHolder>() {
    var list:List<String> = arrayListOf("小裙子","上衣","下装","外套","配件","包包","装扮","居家宅品","办公文具"
    ,"数码周边","游戏专区")

    var mSelect:Int=0

   lateinit var leftclick:leftClick

    fun ClickItem(click: leftClick){
        this.leftclick=click
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.left_type, p0, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyHolder, p1: Int) {

        p0.name.text=list.get(p1)
        if(mSelect==p1){
            p0.layout.setBackgroundColor(R.drawable.type_item_background_selector)
            p0.name.setTextColor(Color.parseColor("#fd3f3f"))
        }else{
            p0.layout.setBackgroundColor(R.drawable.bg2)
            p0.name.setTextColor(Color.parseColor("#323437"))
        }


        p0.name.setOnClickListener {
            leftclick.setOnClick(p1)
        }

    }

    fun channelAdapter(postion:Int){
        if(postion!=null){
            mSelect=postion
            notifyDataSetChanged()
        }
    }

    inner class MyHolder(item: View):RecyclerView.ViewHolder(item){

        var name=item.left_tv
        var layout=item.left_layout
    }
    interface leftClick{
        fun setOnClick(index:Int)
    }
}