package com.example.shopping_gradle.Controller.Adapter.SortTypeAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Model.Entity.MyleftTypeBean
import com.example.shopping_gradle.R
import retrofit2.Response

class TypeRightAdapter(
    context: Context?,
    response: Response<MyleftTypeBean>?
):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mlist=response
    var ctx=context


        var currtype= HOTTYPE
    companion object{
        var HOTTYPE:Int=0
        var RECOMMEND_TYPE:Int=1
    }
    override fun getItemViewType(position: Int): Int {
       when(position){
           HOTTYPE -> currtype=HOTTYPE
           RECOMMEND_TYPE->currtype= RECOMMEND_TYPE
       }
        return currtype
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        if(getItemViewType(currtype)== HOTTYPE){

        }else if(getItemViewType(currtype)== RECOMMEND_TYPE){

        }
        return MyHotHolder(LayoutInflater.from(p0.context).inflate(R.layout.right_type,p0,false))
    }

    override fun getItemCount(): Int {
        return mlist!!.body().result!!.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
    }




    inner class MyRecommendHolder(item:View):RecyclerView.ViewHolder(item){

    }

    inner class MyHotHolder(item: View):RecyclerView.ViewHolder(item){

    }

}