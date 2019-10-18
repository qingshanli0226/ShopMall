package com.example.homework1.Controller.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.homework1.R


class MyAdapter2 : PagerAdapter {


    var datas : ArrayList<String>
    var context : Context

    constructor(datas: ArrayList<String>, context: Context) : super() {
        this.datas = datas
        this.context = context
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(context, R.layout.layout_pageritem1, null)
        var imageView : ImageView = view.findViewById(R.id.iv_image)
        Glide.with(context)
            .load(datas[position])
            .into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
       return datas.size
    }
}