package com.atguigu.shoppingmall.type.bean

/**
 * Created by Administrator on 2016/10/3.
 */
data class TypeBean(var code: Int = 0,
                    var msg: String,
                    var result: List<ResultBean>) {

    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"3","parent_id":"0","name":"小裙子","pic":"","is_deleted":"0","child":[{"p_catalog_id":"10","parent_id":"3","name":"古风","pic":"http://brand.cycangcdn.com/product_catalog/1446016297307.jpg","is_deleted":"0"},{"p_catalog_id":"11","parent_id":"3","name":"和风","pic":"http://brand.cycangcdn.com/product_catalog/1446016509141.jpg","is_deleted":"0"},{"p_catalog_id":"12","parent_id":"3","name":"lolita","pic":"http://brand.cycangcdn.com/product_catalog/1446016728621.jpg","is_deleted":"0"},{"p_catalog_id":"91","parent_id":"3","name":"日常","pic":"http://brand.cycangcdn.com/product_catalog/1446444753067.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"2704","channel_id":"6","brand_id":"230","p_catalog_id":"3","supplier_type":"1","supplier_code":"0","name":"中华风lolita -山海经 凤凰图  JSK","cover_price":"300.00","brief":"","figure":"http://f.p.cycangcdn.com/1447239453626.jpg","sell_time_start":"1447171200","sell_time_end":"1447776000"},{"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"http://f.p.cycangcdn.com/1457504361484.jpg","sell_time_start":"1457452800","sell_time_end":"1458057600"},{"product_id":"3571","channel_id":"8","brand_id":"259","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801005","name":"【INFANTA.婴梵塔】学院风尖领外套/大衣","cover_price":"287.00","brief":"","figure":"http://f.p.cycangcdn.com/1450433177397.jpg","sell_time_start":"1450368000","sell_time_end":"1450972800"},{"product_id":"7729","channel_id":"8","brand_id":"432","p_catalog_id":"91","supplier_type":"2","supplier_code":"1601008","name":"现货【TUMO】 雨库洛牌元素 软妹森女系短袖连衣裙","cover_price":"179.00","brief":"","figure":"http://f.p.cycangcdn.com/supplier/1467687105197.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"5383","channel_id":"3","brand_id":"77","p_catalog_id":"91","supplier_type":"2","supplier_code":"2105003","name":"【宅漫周边店】猫咪后院 衬衫+背带裙2件套装 超软萌","cover_price":"149.00","brief":"","figure":"http://f.p.cycangcdn.com/1458285839004.jpg","sell_time_start":"1458230400","sell_time_end":"1458835200"},{"product_id":"589","channel_id":"8","brand_id":"266","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801001","name":"定制【Neverland 原创】直发 烫金lolita 人鱼之歌 高腰JSK连衣裙","cover_price":"458.00","brief":"45天工期","figure":"http://f.p.cycangcdn.com/1438772068067.jpg","sell_time_start":"1438704000","sell_time_end":"1439308800"}]}]
     */


    /**
     * p_catalog_id : 3
     * parent_id : 0
     * name : 小裙子
     * pic :
     * is_deleted : 0
     * child : [{"p_catalog_id":"10","parent_id":"3","name":"古风","pic":"http://brand.cycangcdn.com/product_catalog/1446016297307.jpg","is_deleted":"0"},{"p_catalog_id":"11","parent_id":"3","name":"和风","pic":"http://brand.cycangcdn.com/product_catalog/1446016509141.jpg","is_deleted":"0"},{"p_catalog_id":"12","parent_id":"3","name":"lolita","pic":"http://brand.cycangcdn.com/product_catalog/1446016728621.jpg","is_deleted":"0"},{"p_catalog_id":"91","parent_id":"3","name":"日常","pic":"http://brand.cycangcdn.com/product_catalog/1446444753067.jpg","is_deleted":"0"}]
     * hot_product_list : [{"product_id":"2704","channel_id":"6","brand_id":"230","p_catalog_id":"3","supplier_type":"1","supplier_code":"0","name":"中华风lolita -山海经 凤凰图  JSK","cover_price":"300.00","brief":"","figure":"http://f.p.cycangcdn.com/1447239453626.jpg","sell_time_start":"1447171200","sell_time_end":"1447776000"},{"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"http://f.p.cycangcdn.com/1457504361484.jpg","sell_time_start":"1457452800","sell_time_end":"1458057600"},{"product_id":"3571","channel_id":"8","brand_id":"259","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801005","name":"【INFANTA.婴梵塔】学院风尖领外套/大衣","cover_price":"287.00","brief":"","figure":"http://f.p.cycangcdn.com/1450433177397.jpg","sell_time_start":"1450368000","sell_time_end":"1450972800"},{"product_id":"7729","channel_id":"8","brand_id":"432","p_catalog_id":"91","supplier_type":"2","supplier_code":"1601008","name":"现货【TUMO】 雨库洛牌元素 软妹森女系短袖连衣裙","cover_price":"179.00","brief":"","figure":"http://f.p.cycangcdn.com/supplier/1467687105197.jpg","sell_time_start":"0","sell_time_end":"0"},{"product_id":"5383","channel_id":"3","brand_id":"77","p_catalog_id":"91","supplier_type":"2","supplier_code":"2105003","name":"【宅漫周边店】猫咪后院 衬衫+背带裙2件套装 超软萌","cover_price":"149.00","brief":"","figure":"http://f.p.cycangcdn.com/1458285839004.jpg","sell_time_start":"1458230400","sell_time_end":"1458835200"},{"product_id":"589","channel_id":"8","brand_id":"266","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801001","name":"定制【Neverland 原创】直发 烫金lolita 人鱼之歌 高腰JSK连衣裙","cover_price":"458.00","brief":"45天工期","figure":"http://f.p.cycangcdn.com/1438772068067.jpg","sell_time_start":"1438704000","sell_time_end":"1439308800"}]
     */



   inner class ResultBean(
            var p_catalog_id: String,
            var parent_id: String,
            var name: String,
            var pic: String,
            var is_deleted: String,
            var child: List<ChildBean>,
            var hot_product_list: List<HotProductListBean>
    ) {

        /**
         * p_catalog_id : 10
         * parent_id : 3
         * name : 古风
         * pic : http://brand.cycangcdn.com/product_catalog/1446016297307.jpg
         * is_deleted : 0
         */


        /**
         * product_id : 2704
         * channel_id : 6
         * brand_id : 230
         * p_catalog_id : 3
         * supplier_type : 1
         * supplier_code : 0
         * name : 中华风lolita -山海经 凤凰图  JSK
         * cover_price : 300.00
         * brief :
         * figure : http://f.p.cycangcdn.com/1447239453626.jpg
         * sell_time_start : 1447171200
         * sell_time_end : 1447776000
         */



       inner class ChildBean (
                var p_catalog_id: String,
                var parent_id: String,
                var name: String,
                var pic: String,
                var is_deleted: String){

        }

        inner class HotProductListBean(
                var product_id: String,
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
        ) {

        }
    }
}
