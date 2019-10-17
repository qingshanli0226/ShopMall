package com.example.shoppingmall_kotlin.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

class ActAdapter(image:List<String>,context: Context):PagerAdapter(){
    var image:List<String>?=null
    var context:Context?=null
    init {
        this.image = image
        this.context = context
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = ImageView(context)
        val scaleType = ImageView.ScaleType.FIT_XY
        Glide.with(context).load(image?.get(position)).into(view)
        container.addView(view)
        return view
    }



    override fun getCount(): Int {
        return image!!.size
    }

}
