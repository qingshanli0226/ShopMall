package com.atguigu.shoppingmall.home.uitls

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by zhy on 16/5/7.
 */
abstract class BasePageTransformer : ViewPager.PageTransformer {
    protected var mPageTransformer: ViewPager.PageTransformer? = NonPageTransformer.INSTANCE

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun transformPage(view: View, position: Float) {
        if (mPageTransformer != null) {
            mPageTransformer!!.transformPage(view, position)
        }

        pageTransform(view, position)
    }

    protected abstract fun pageTransform(view: View, position: Float)

    companion object {
        val DEFAULT_CENTER = 0.5f
    }


}
