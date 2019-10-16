package com.atguigu.shoppingmall.utils

import android.content.Context

/**
 * 作者：尚硅谷-杨光福 on 2016/8/13 15:00
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：单位转换工具
 * px和dp互相转换工具
 */
object DensityUtil {
    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}
