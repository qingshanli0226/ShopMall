package com.example.shopping_gradle.Controller.Adapter.HomeAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shopping_gradle.Model.Entity.RecommendInfo
import com.example.shopping_gradle.Model.Utlis.MyConstants
import com.example.shopping_gradle.R
import kotlinx.android.synthetic.main.recommnd_item.view.*
import kotlinx.android.synthetic.main.seckll_item.view.*

class RecommndAdapter(
    ctx:Context,
    baen: List<RecommendInfo>
): RecyclerView.Adapter<RecommndAdapter.MyHolder>() {

    var mCtx=ctx
    var mListRecommnd=baen
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyHolder {

        val view:View=LayoutInflater.from(p0.context).inflate(R.layout.recommnd_item,p0,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return mListRecommnd.size
    }

    override fun onBindViewHolder(holder: MyHolder, p1: Int) {

        holder.names.text=mListRecommnd.get(p1).name
        holder.price.text=mListRecommnd.get(p1).cover_price
        Glide.with(mCtx).load(MyConstants.BASE_URl_IMAGE+mListRecommnd.get(p1).figure).into(holder.img)

    }

    inner class MyHolder(item: View):RecyclerView.ViewHolder(item){

        var names=item.tv_name
        var price=item.tv_price
        var img=item.iv_recommend
    }
}