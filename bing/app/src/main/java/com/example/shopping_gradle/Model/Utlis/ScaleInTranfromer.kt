package com.example.shopping_gradle.Model.Utlis

import android.annotation.TargetApi
import android.os.Build
import android.support.v4.view.ViewPager
import android.view.View

class ScaleInTranfromer: BasePageTransfromer() {


    private val DEFAULT_MIN_SCALE = 0.85f
    private var mMinScale = DEFAULT_MIN_SCALE




    override fun pageTansFrom(view: View, position: Float) {
        val pageWidth = view.width
        val pageHeight = view.height

        view.pivotY = (pageHeight / 2).toFloat()
        view.pivotX = (pageWidth / 2).toFloat()
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.scaleX = mMinScale
            view.scaleY = mMinScale
            view.pivotX = pageWidth.toFloat()
        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            if (position < 0)
            //1-2:1[0,-1] ;2-1:1[-1,0]
            {

                val scaleFactor = (1 + position) * (1 - mMinScale) + mMinScale
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

                view.pivotX = pageWidth * (DEFAULT_CENTER + DEFAULT_CENTER * -position)

            } else
            //1-2:2[1,0] ;2-1:2[0,1]
            {
                val scaleFactor = (1 - position) * (1 - mMinScale) + mMinScale
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
                view.pivotX = pageWidth * ((1 - position) * DEFAULT_CENTER)
            }


        } else { // (1,+Infinity]
            view.pivotX = 0f
            view.scaleX = mMinScale
            view.scaleY = mMinScale
        }
    }





}