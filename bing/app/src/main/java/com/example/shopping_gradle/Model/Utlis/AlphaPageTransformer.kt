package com.example.shopping_gradle.Model.Utlis

import android.support.v4.view.ViewPager
import android.view.View

class AlphaPageTransformer() : BasePageTransfromer() {
    companion object{
      private  val DEFALUT_MIN_ALPHA:Float=0.5f
    }
    private val mMianAlpha= DEFALUT_MIN_ALPHA
//    fun AlphaPageTransformer(pageTransformer: ViewPager.PageTransformer): ??? {
//        this(DEFALUT_MIN_ALPHA,pageTransformer)
//    }
    override fun pageTansFrom(view: View, postion: Float) {
        view.scaleX=0.999f
        if(postion<-1){
            view.alpha=mMianAlpha
        }else if(postion<=1){
            if(postion<0){
                var factor:Float=mMianAlpha+(1-mMianAlpha)*(1+postion)
                view.alpha=factor
            }else{
                val factor:Float=mMianAlpha+(1-mMianAlpha)*(1-postion)
                view.alpha=factor
            }
        }else{
            view.alpha=mMianAlpha
        }
    }

}