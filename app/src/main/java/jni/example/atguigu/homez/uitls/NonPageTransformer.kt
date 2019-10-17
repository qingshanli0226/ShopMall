package com.atguigu.shoppingmall.home.uitls

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by zhy on 16/5/7.
 */
class NonPageTransformer : ViewPager.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.scaleX = 0.8f//hack
    }

    companion object {

        val INSTANCE: ViewPager.PageTransformer = NonPageTransformer()
    }
}
