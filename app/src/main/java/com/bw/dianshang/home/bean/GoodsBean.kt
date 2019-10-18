package com.bw.dianshang.home.bean

import java.io.Serializable

/**
 * child商品类
 */
class GoodsBean : Serializable {

    var name:String? = null
    var cover_price:String? = null
    var figure:String? = null
    var product_id:String? = null
    var number:Int = 1

    /**
     * 是否处于编辑状态
     */
    var isChildSelected:Boolean? = null

    constructor()

    constructor(name: String?, cover_price: String?, figure: String?, product_id: String?) {
        this.name = name
        this.cover_price = cover_price
        this.figure = figure
        this.product_id = product_id
    }

    override fun toString(): String {
        return "GoodsBean(name=$name, cover_price=$cover_price, figure=$figure, product_id=$product_id, number=$number, isChildSelected=$isChildSelected)"
    }


}