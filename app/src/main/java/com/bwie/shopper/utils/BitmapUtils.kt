package com.atguigu.shoppingmall.utils

import android.graphics.*

/**
 * Created by shkstart on 2016/9/24 0024.
 */
object BitmapUtils {

    //提供一个圆形的Bitmap对象
    fun circleBitmap(source: Bitmap): Bitmap {
        //获取图片的宽度
        val width = source.width
        //创建一个与source等宽的Bitmap对象
        val bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888)
        //创建等大小的画布
        val canvas = Canvas(bitmap)
        //绘制一个圆圈：将此圆圈理解为下层图片
        val paint = Paint()
        paint.isAntiAlias = true
        canvas.drawCircle((width / 2).toFloat(), (width / 2).toFloat(), (width / 2).toFloat(), paint)

        //设置图片相交情况下的处理方式
        //setXfermode：设置当绘制的图像出现相交情况时候的处理方式的,它包含的常用模式有哪几种
        //PorterDuff.Mode.SRC_IN 取两层图像交集部门,只显示上层图像,注意这里是指取相交叉的部分,然后显示上层图像
        //PorterDuff.Mode.DST_IN 取两层图像交集部门,只显示下层图像
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN) as Xfermode
        //source:理解为上层图片
        canvas.drawBitmap(source, 0f, 0f, paint)

        return bitmap
    }

    //图片的压缩
    fun zoom(source: Bitmap, w: Float, h: Float): Bitmap {//参数2，3：不能声明int
        val matrix = Matrix()
        matrix.postScale(w / source.width, h / source.height)

        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)

    }

}
