package com.atguigu.shoppingmall.home.bean

import java.io.Serializable
import kotlin.String as String1

/**
 * Created by Administrator on 2016/10/9.
 * child商品类
 */
class GoodsBean : Serializable {

    var name: String1? = null
    var cover_price: String1? = null
    var figure: String1? = null
    var product_id: String1? = null
    var number = 1

    /**
     * 是否处于编辑状态
     */
    var isEditing: Boolean = false
    /**
     * 是否被选中
     */
    var isChildSelected: Boolean = false

    constructor() {}

    constructor(name: String1?, cover_price: String1?, figure: String1?, product_id: String1?) {
        this.name = name
        this.cover_price = cover_price
        this.figure = figure
        this.product_id = product_id
    }

    fun getCoveprice():String1 {
        cover_price!!.substring(0, cover_price!!.length - 1)
        return cover_price as kotlin.String
    }

    fun setCoverprice(cover_price: String1) {
        this.cover_price = cover_price
    }

    override fun toString(): String1 {
        return "GoodsBean{" +
                "name='" + name + '\''.toString() +
                ", cover_price='" + cover_price + '\''.toString() +
                ", figure='" + figure + '\''.toString() +
                ", product_id='" + product_id + '\''.toString() +
                ", number=" + number +
                ", isEditing=" + isEditing +
                ", isChildSelected=" + isChildSelected +
                '}'.toString()
    }
}
