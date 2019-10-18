package com.bw.dianshang.home.utils

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

class ScalelnTransformer : BasePageTransformer() {

    final val DEFAULT_MIN_SCALE:Float = 0.85f
    var mMinScale:Float = DEFAULT_MIN_SCALE

    fun ScalelnTransformer(){}

    fun ScalelnTransformer(minScale:Float){}

    fun ScalelnTransformer(pageTransformer: ViewPager.PageTransformer){}

    fun ScalelnTransformer(minScale: Float,pageTransformer: ViewPager.PageTransformer){
        mMinScale = minScale
        mPageTransformer = pageTransformer
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun pageTransform(view: View, position: Float) {
        var pageWidth:Int = view.width
        var pageHeight:Int = view.height

        view.pivotX = ((pageWidth / 2).toFloat())
        view.pivotY = ((pageHeight / 2).toFloat())

        if (position < -1){//[-1,1]
            //This page is way off-screen to the left
            view.scaleX = mMinScale
            view.scaleY = mMinScale
            view.pivotX = pageWidth.toFloat()
        }else if (position <= 1){//[-1,1]
            //Modify the default slide transition to shrink the page as well
            if (position < 0){//1-2;1[0,-1];2-1;1[-1,0]
                var scaleFactor:Float = (1 + position) * (1 - mMinScale) + mMinScale

                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

                view.pivotX = (pageWidth * (DEFAULT_MIN_SCALE + (DEFAULT_MIN_SCALE * -position)))
            }else{
                var scaleFactor:Float = (1 - position) * (1 - mMinScale) + mMinScale

                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

                view.pivotX = (pageWidth * ((1 - position) * DEFAULT_MIN_SCALE))
            }
        }else{//(1,+Infinity)
            view.pivotX = 0F
            view.scaleX = mMinScale
            view.scaleY = mMinScale
        }

    }
}