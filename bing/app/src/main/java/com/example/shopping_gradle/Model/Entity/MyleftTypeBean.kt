package com.example.shopping_gradle.Model.Entity

class MyleftTypeBean {


    /**
     * code : 200
     * msg : 请求成功
     * result : [{"p_catalog_id":"3","parent_id":"0","name":"小裙子","pic":"","is_deleted":"0","child":[{"p_catalog_id":"10","parent_id":"3","name":"古风","pic":"/product_catalog/1446016297307.jpg","is_deleted":"0"},{"p_catalog_id":"11","parent_id":"3","name":"和风","pic":"/product_catalog/1446016509141.jpg","is_deleted":"0"},{"p_catalog_id":"12","parent_id":"3","name":"lolita","pic":"/product_catalog/1446016728621.jpg","is_deleted":"0"},{"p_catalog_id":"91","parent_id":"3","name":"日常","pic":"/product_catalog/1446444753067.jpg","is_deleted":"0"}],"hot_product_list":[{"product_id":"2704","channel_id":"6","brand_id":"230","p_catalog_id":"3","supplier_type":"1","supplier_code":"0","name":"中华风lolita -山海经 凤凰图  JSK","cover_price":"300.00","brief":"","figure":"/1447239453626.jpg","sell_time_start":"1447171200","sell_time_end":"1447776000"},{"product_id":"3571","channel_id":"8","brand_id":"259","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801005","name":"【INFANTA.婴梵塔】学院风尖领外套/大衣","cover_price":"287.00","brief":"","figure":"/1450433177397.jpg","sell_time_start":"1450368000","sell_time_end":"1450972800"},{"product_id":"589","channel_id":"8","brand_id":"266","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801001","name":"定制【Neverland 原创】直发 烫金lolita 人鱼之歌 高腰JSK连衣裙","cover_price":"403.04","brief":"45天工期","figure":"/1438772068067.jpg","sell_time_start":"1478772000","sell_time_end":"1439308800"},{"product_id":"5970","channel_id":"8","brand_id":"356","p_catalog_id":"91","supplier_type":"2","supplier_code":"1101004","name":"【绝对萌域】 黑白猫咪 黑喵咪 短袖连衣裙","cover_price":"121.44","brief":"猫咪是神秘的小动物。传说它们能通灵和驱魔，给主人带来好运。可猫咪也被认为是女巫役使的邪恶精灵，是恶魔的化身。萌域为爱美又略微中二的宅MM们，设计了黑猫、白猫主题裙装，不同的风格、一样的可爱。白猫套装穿上性感俏皮，黑色连衣裙让你像猫咪一样优雅又有气质。","figure":"/1463385771450.jpg","sell_time_start":"1478772000","sell_time_end":"1461254400"},{"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"/1457504361484.jpg","sell_time_start":"1457452800","sell_time_end":"1458057600"},{"product_id":"7729","channel_id":"8","brand_id":"432","p_catalog_id":"91","supplier_type":"2","supplier_code":"1601008","name":"现货【TUMO】 雨库洛牌元素 软妹森女系短袖连衣裙","cover_price":"179.00","brief":"","figure":"/supplier/1467687105197.jpg","sell_time_start":"0","sell_time_end":"0"}]}]
     */

    var code: Int = 0
    var msg: String? = null
    var result: List<ResultBean>? = null

    class ResultBean {
        /**
         * p_catalog_id : 3
         * parent_id : 0
         * name : 小裙子
         * pic :
         * is_deleted : 0
         * child : [{"p_catalog_id":"10","parent_id":"3","name":"古风","pic":"/product_catalog/1446016297307.jpg","is_deleted":"0"},{"p_catalog_id":"11","parent_id":"3","name":"和风","pic":"/product_catalog/1446016509141.jpg","is_deleted":"0"},{"p_catalog_id":"12","parent_id":"3","name":"lolita","pic":"/product_catalog/1446016728621.jpg","is_deleted":"0"},{"p_catalog_id":"91","parent_id":"3","name":"日常","pic":"/product_catalog/1446444753067.jpg","is_deleted":"0"}]
         * hot_product_list : [{"product_id":"2704","channel_id":"6","brand_id":"230","p_catalog_id":"3","supplier_type":"1","supplier_code":"0","name":"中华风lolita -山海经 凤凰图  JSK","cover_price":"300.00","brief":"","figure":"/1447239453626.jpg","sell_time_start":"1447171200","sell_time_end":"1447776000"},{"product_id":"3571","channel_id":"8","brand_id":"259","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801005","name":"【INFANTA.婴梵塔】学院风尖领外套/大衣","cover_price":"287.00","brief":"","figure":"/1450433177397.jpg","sell_time_start":"1450368000","sell_time_end":"1450972800"},{"product_id":"589","channel_id":"8","brand_id":"266","p_catalog_id":"12","supplier_type":"2","supplier_code":"1801001","name":"定制【Neverland 原创】直发 烫金lolita 人鱼之歌 高腰JSK连衣裙","cover_price":"403.04","brief":"45天工期","figure":"/1438772068067.jpg","sell_time_start":"1478772000","sell_time_end":"1439308800"},{"product_id":"5970","channel_id":"8","brand_id":"356","p_catalog_id":"91","supplier_type":"2","supplier_code":"1101004","name":"【绝对萌域】 黑白猫咪 黑喵咪 短袖连衣裙","cover_price":"121.44","brief":"猫咪是神秘的小动物。传说它们能通灵和驱魔，给主人带来好运。可猫咪也被认为是女巫役使的邪恶精灵，是恶魔的化身。萌域为爱美又略微中二的宅MM们，设计了黑猫、白猫主题裙装，不同的风格、一样的可爱。白猫套装穿上性感俏皮，黑色连衣裙让你像猫咪一样优雅又有气质。","figure":"/1463385771450.jpg","sell_time_start":"1478772000","sell_time_end":"1461254400"},{"product_id":"5181","channel_id":"6","brand_id":"394","p_catalog_id":"10","supplier_type":"2","supplier_code":"1101037","name":"【画影】汉元素 古风日常\u2014\u2014 仲夏 ","cover_price":"250.00","brief":"","figure":"/1457504361484.jpg","sell_time_start":"1457452800","sell_time_end":"1458057600"},{"product_id":"7729","channel_id":"8","brand_id":"432","p_catalog_id":"91","supplier_type":"2","supplier_code":"1601008","name":"现货【TUMO】 雨库洛牌元素 软妹森女系短袖连衣裙","cover_price":"179.00","brief":"","figure":"/supplier/1467687105197.jpg","sell_time_start":"0","sell_time_end":"0"}]
         */

        var p_catalog_id: String? = null
        var parent_id: String? = null
        var name: String? = null
        var pic: String? = null
        var is_deleted: String? = null
        var child: List<ChildBean>? = null
        var hot_product_list: List<HotProductListBean>? = null

        class ChildBean {
            /**
             * p_catalog_id : 10
             * parent_id : 3
             * name : 古风
             * pic : /product_catalog/1446016297307.jpg
             * is_deleted : 0
             */

            var p_catalog_id: String? = null
            var parent_id: String? = null
            var name: String? = null
            var pic: String? = null
            var is_deleted: String? = null
        }

        class HotProductListBean {
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
             * figure : /1447239453626.jpg
             * sell_time_start : 1447171200
             * sell_time_end : 1447776000
             */

            var product_id: String? = null
            var channel_id: String? = null
            var brand_id: String? = null
            var p_catalog_id: String? = null
            var supplier_type: String? = null
            var supplier_code: String? = null
            var name: String? = null
            var cover_price: String? = null
            var brief: String? = null
            var figure: String? = null
            var sell_time_start: String? = null
            var sell_time_end: String? = null
        }
    }
}
