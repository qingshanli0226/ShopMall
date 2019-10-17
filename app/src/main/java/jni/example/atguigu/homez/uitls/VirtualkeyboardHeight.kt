package com.atguigu.shoppingmall.home.uitls

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

import java.lang.reflect.Method

/**
 * Created by Administrator on 2016/11/13.
 */
// 获取虚拟键盘的高度
object VirtualkeyboardHeight {

    // 获取屏幕和底部的高度
    fun getDpi(context: Context): Int {

        var dpi = 0
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val displayMetrics = DisplayMetrics()

        val c: Class<*>
        try {
            c = Class.forName("android.view.Display")
            val method = c.getMethod("getRealMetrics", DisplayMetrics::class.java!!)
            method.invoke(display, displayMetrics)
            dpi = displayMetrics.heightPixels
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dpi
    }

    // 获取底部虚拟键盘的高度
    fun getBottomStatusHeight(context: Context): Int {
        val totalHeight = getDpi(context)

        val contentHeight = getScreenHeight(context)

        return totalHeight - contentHeight
    }

    // 获取屏幕的高度
    fun getScreenHeight(context: Context): Int {

        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)

        return outMetrics.heightPixels
    }
}

