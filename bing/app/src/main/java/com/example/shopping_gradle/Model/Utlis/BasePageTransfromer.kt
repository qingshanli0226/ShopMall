package com.example.shopping_gradle.Model.Utlis

import android.support.v4.view.ViewPager
import android.view.View

abstract class BasePageTransfromer:ViewPager.PageTransformer {
    protected var mPageTranformer:ViewPager.PageTransformer=NonPageTransformer.INSTANCE
    companion object{
        val DEFAULT_CENTER:Float =0.5f
    }
    override fun transformPage(p0: View, p1: Float) {
        if(mPageTranformer!=null){
            mPageTranformer.transformPage(p0, p1)
        }
        pageTansFrom(p0,p1)

    }
    protected abstract fun pageTansFrom(view:View,postion:Float)
}