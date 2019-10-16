package com.atguigu.shoppingmall.shoppingcart.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.widget.TintTypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.atguigu.shoppingmall.R


/**
 * Created by Administrator on 2016/8/31.
 */
@SuppressLint("RestrictedApi")
class NumberAddSubView @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {
    private val btn_sub: ImageView
    private val btn_add: ImageView
    private val tv_count: TextView
    var value = 1
    var minValue = 1
    var maxValue = 10

    private var onNumberChangeListener: OnNumberChangeListener? = null

    fun getValu(): Int {
        val countStr = tv_count.text.toString().trim { it <= ' ' }//文本内容
        if (countStr != null) {
            value = Integer.valueOf(countStr)
        }
        return value
    }

    fun setValu(value: Int) {
        this.value = value
        tv_count.text = value.toString()
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : this(context, attrs, 0) {
    }


    init {
        //把布局和当前类形成整体
        View.inflate(context, R.layout.number_add_sub_layout, this)
        btn_sub = findViewById(R.id.btn_sub)
        btn_add = findViewById(R.id.btn_add)
        tv_count = findViewById(R.id.tv_count)

        getValu()

        //设置点击事件
        btn_add.setOnClickListener(this)
        btn_sub.setOnClickListener(this)

        if (attrs != null) {
            //取出属性
            val tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.NumberAddSubView)
            val value = tintTypedArray.getInt(R.styleable.NumberAddSubView_value, 0)
            if (value > 0) {
                setValu(value)
            }
            var minValue = tintTypedArray.getInt(R.styleable.NumberAddSubView_minValue, 0)
            if (value > 0) {
                this.minValue = minValue
            }
            var maxValue = tintTypedArray.getInt(R.styleable.NumberAddSubView_maxValue, 0)
            if (value > 0) {
                this.maxValue = maxValue
            }
            val addDrawable = tintTypedArray.getDrawable(R.styleable.NumberAddSubView_numberAddBackground)
            if (addDrawable != null) {
                btn_add.setImageDrawable(addDrawable)
            }
            val subDrawable = tintTypedArray.getDrawable(R.styleable.NumberAddSubView_numberSubBackground)
            if (subDrawable != null) {
                btn_sub.setImageDrawable(subDrawable)
            }
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_add) {
            //加
            addNumber()
            if (onNumberChangeListener != null) {
                onNumberChangeListener!!.addNumber(v, value)
            }
        } else {
            //减
            subNumber()
            if (onNumberChangeListener != null) {
                onNumberChangeListener!!.subNumner(v, value)
            }
        }
    }

    private fun subNumber() {
        if (value > minValue) {
            value -= 1
        }
        setValu(value)

    }

    private fun addNumber() {
        if (value < maxValue) {
            value += 1
        }
        setValu(value)
    }

    interface OnNumberChangeListener {
        //当按钮被点击的时候回调
        fun addNumber(view: View, value: Int)

        fun subNumner(view: View, value: Int)
    }

    fun setOnNumberChangeListener(onNumberChangeListener: OnNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener
    }
}
