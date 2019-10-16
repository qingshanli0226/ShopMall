package com.atguigu.shoppingmall.home.uitls

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

class AlphaPageTransformer : BasePageTransformer {
    private var mMinAlpha = DEFAULT_MIN_ALPHA

    constructor() {}

    constructor(pageTransformer: ViewPager.PageTransformer) : this(DEFAULT_MIN_ALPHA, pageTransformer) {}

    @JvmOverloads
    constructor(minAlpha: Float, pageTransformer: ViewPager.PageTransformer = NonPageTransformer.INSTANCE) {
        mMinAlpha = minAlpha
        mPageTransformer = pageTransformer
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public override fun pageTransform(view: View, position: Float) {
        view.scaleX = 0.999f//hack

        if (position < -1) { // [-Infinity,-1)
            view.alpha = mMinAlpha
        } else if (position <= 1) { // [-1,1]

            if (position < 0)
            //[0，-1]
            {           //[1,min]
                val factor = mMinAlpha + (1 - mMinAlpha) * (1 + position)
                view.alpha = factor
            } else
            //[1，0]
            {
                //[min,1]
                val factor = mMinAlpha + (1 - mMinAlpha) * (1 - position)
                view.alpha = factor
            }
        } else { // (1,+Infinity]
            view.alpha = mMinAlpha
        }
    }

    companion object {
        private val DEFAULT_MIN_ALPHA = 0.5f
    }
}
