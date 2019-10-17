package com.atguigu.shoppingmall.home.bean

import com.alibaba.fastjson.JSONObject

/**
 * Created by Administrator on 2016/9/28.
 */
class ResultBean : JSONObject() {

    var seckill_info: SeckillInfoBean? = null
    var banner_info: List<BannerInfoBean>? = null
    var channel_info: List<ChannelInfoBean>? = null
    var act_info: List<ActInfoBean>? = null
    lateinit var hot_info: List<HotInfoBean>
    var recommend_info: List<RecommendInfoBean>? = null

    class SeckillInfoBean {
        var start_time: String? = null
        var end_time: String? = null
        var list: List<ListBean>? = null

        override fun toString(): String {
            return "SeckillInfoBean{" +
                    "start_time='" + start_time + '\''.toString() +
                    ", end_time='" + end_time + '\''.toString() +
                    ", list=" + list +
                    '}'.toString()
        }

        class ListBean {
            var product_id: String? = null
            var name: String? = null
            var cover_price: String? = null
            var origin_price: String? = null
            var figure: String? = null
        }
    }

    class BannerInfoBean {
        var image: String? = null
        var option: Int = 0
        var type: Int = 0
        var valueBean: ValueBean? = null

        override fun toString(): String {
            return "BannerInfoBean{" +
                    "image='" + image + '\''.toString() +
                    ", option=" + option +
                    ", type=" + type +
                    ", valueBean=" + valueBean +
                    '}'.toString()
        }

        class ValueBean {
            var url: String? = null
            var product_id: String? = null
            var brand_id: String? = null

            override fun toString(): String {
                return "ValueBean{" +
                        "url='" + url + '\''.toString() +
                        ", product_id='" + product_id + '\''.toString() +
                        ", brand_id='" + brand_id + '\''.toString() +
                        '}'.toString()
            }
        }
    }

    class ChannelInfoBean {
        var option: Int = 0
        var type: Int = 0
        var channel_name: String? = null
        var image: String? = null

        private val value: ValueBean? = null


        class ValueBean {
            private val channel_id: String? = null

        }

        override fun toString(): String {
            return "ChannelInfoBean{" +
                    "option=" + option +
                    ", type=" + type +
                    ", channel_name='" + channel_name + '\''.toString() +
                    ", image='" + image + '\''.toString() +
                    ", value=" + value +
                    '}'.toString()
        }
    }

    class ActInfoBean {
        var name: String? = null
        var icon_url: String? = null
        var url: String? = null

        override fun toString(): String {
            return "ActInfoBean{" +
                    "name='" + name + '\''.toString() +
                    ", icon_url='" + icon_url + '\''.toString() +
                    ", url='" + url + '\''.toString() +
                    '}'.toString()
        }
    }

    class HotInfoBean {
        var product_id: String? = null
        var name: String? = null
        var cover_price: String? = null
        var figure: String? = null

        override fun toString(): String {
            return "HotInfoBean{" +
                    "product_id='" + product_id + '\''.toString() +
                    ", name='" + name + '\''.toString() +
                    ", cover_price='" + cover_price + '\''.toString() +
                    ", figure='" + figure + '\''.toString() +
                    '}'.toString()
        }
    }

    class RecommendInfoBean {
        var product_id: String? = null
        var name: String? = null
        var cover_price: String? = null
        var figure: String? = null

        override fun toString(): String {
            return "RecommendInfoBean{" +
                    "product_id='" + product_id + '\''.toString() +
                    ", name='" + name + '\''.toString() +
                    ", cover_price='" + cover_price + '\''.toString() +
                    ", figure='" + figure + '\''.toString() +
                    '}'.toString()
        }
    }

}

