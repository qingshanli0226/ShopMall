package com.bw.dianshang.home.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

abstract class BasePageTransformer : ViewPager.PageTransformer {

    var mPageTransformer:ViewPager.PageTransformer = NonPageTransformer().INSTANCE
    var DEFAULT_CENTER:Float = 0.5f

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun transformPage(view: View, position: Float) {
        if (mPageTransformer != null){
            mPageTransformer.transformPage(view,position)
        }

        pageTransform(view,position)

    }

    abstract fun pageTransform(view: View, position: Float)
}