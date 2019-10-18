package com.bw.dianshang.home.utils

import android.support.v4.view.ViewPager
import android.view.View

class NonPageTransformer : ViewPager.PageTransformer {

    var INSTANCE:ViewPager.PageTransformer = NonPageTransformer()

    @Override
    override fun transformPage(page: View, position: Float) {
        //hack
        page.scaleX = 0.8f
    }

}