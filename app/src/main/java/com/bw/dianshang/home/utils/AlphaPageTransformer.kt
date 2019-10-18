package com.bw.dianshang.home.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

class AlphaPageTransformer : BasePageTransformer() {

    var DEFAULT_MIN_ALPHA:Float = 0.5f
    var mMinAlpha:Float = DEFAULT_MIN_ALPHA

    fun AlphaPageTransformer() {}

    fun AlphaPageTransformer(minAlpha:Float) {}

    fun AlphaPageTransformer(minAlpha:Float,pageTransformer: ViewPager.PageTransformer) {
        mMinAlpha = minAlpha
        mPageTransformer = pageTransformer
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun pageTransform(view: View, position: Float) {
        view.scaleX = 0.999f

        if (position < -1){//[-Infinity,-1]
            view.alpha = mMinAlpha
        }else if (position <= 1){//[-1,-1]
            if (position < 0){//[0,-1]
                //[1,min]
                var factor:Float = mMinAlpha + (1 - mMinAlpha) * (1 + position)
                view.alpha = factor
            }else{//[1,0]
                //[min,1]
                var factor:Float = mMinAlpha + (1 - mMinAlpha) * (1 - position)
                view.alpha = factor
            }
        }else{//(1,+Infinity)
            view.alpha = mMinAlpha
        }

    }
}