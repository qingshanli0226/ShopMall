package com.example.shopping_gradle.Model.Utlis

import android.support.v4.view.ViewPager
import android.view.View

class NonPageTransformer:ViewPager.PageTransformer{
    override fun transformPage(p0: View, p1: Float) {

        p0.scaleX=0.8f
    }
    companion object {
        val INSTANCE: ViewPager.PageTransformer = NonPageTransformer()
    }


}