package com.atguigu.shoppingmall.home.bean

import java.io.Serializable
import kotlin.String as String1

/**
 * Created by Administrator on 2016/10/9.
 * child商品类
 */
data class GoodsBean(
        var name: String1,
        var cover_price: String1,
        var figure: String1,
        var product_id: String1,
        var number:Int = 1,
        var isEditing: Boolean = false,
        var isChildSelected: Boolean = false) : Serializable {

    /**
     * isEditing
     * 是否处于编辑状态
     *
     * isChildSelected
     * 是否被选中
     */

    fun cover_price():String1 {
        cover_price.substring(0, cover_price.length - 1)
        return cover_price
    }

}
