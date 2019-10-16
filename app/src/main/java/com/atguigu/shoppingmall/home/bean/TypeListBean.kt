package com.atguigu.shoppingmall.home.bean

/**
 * Created by Administrator on 2016/10/12.
 */
data class TypeListBean(
        var code: Int = 0,
        var msg: String,
        var result: ResultBean) {

    inner class ResultBean(
            var isCatalog_data: Boolean = false,
            var isBrand_data: Boolean = false,
            var is_recommended: String,
            var page_data: List<PageDataBean>) {

        /**
         * product_id : 4183
         * origin_price : 29.00
         * channel_id : 12
         * brand_id : 77
         * p_catalog_id : 73
         * supplier_type : 1
         * supplier_code : 1101029
         * name : 【Honest首饰】猫爪子戒指 925银 开口指环 萌宠 喵星人耳朵
         * cover_price : 26.00
         * brief :
         * figure : http://f.p.cycangcdn.com/1453345346749.jpg
         * sell_time_start : 1453305600
         * sell_time_end : 1453910400
         */



        inner class PageDataBean (
                var product_id: String,
                var origin_price: String,
                var channel_id: String,
                var brand_id: String,
                var p_catalog_id: String,
                var supplier_type: String,
                var supplier_code: String,
                var name: String,
                var cover_price: String,
                var brief: String,
                var figure: String,
                var sell_time_start: String,
                var sell_time_end: String
        )
    }
}
