package com.atguigu.shoppingmall.home.bean

import com.alibaba.fastjson.JSONObject
import com.google.gson.annotations.SerializedName


/**
 * Created by Administrator on 2016/9/28.
 */
data class ResultBean(
    @SerializedName("code")
    var code: Int, // 200
    @SerializedName("msg")
    var msg: String, // 请求成功
    @SerializedName("result")
    var result: Result
) {
    data class Result(
        @SerializedName("act_info")
        var actInfo: List<ActInfo>,
        @SerializedName("banner_info")
        var bannerInfo: List<BannerInfo>,
        @SerializedName("channel_info")
        var channelInfo: List<ChannelInfo>,
        @SerializedName("hot_info")
        var hotInfo: List<HotInfo>,
        @SerializedName("recommend_info")
        var recommendInfo: List<RecommendInfo>,
        @SerializedName("seckill_info")
        var seckillInfo: SeckillInfo
    ) {
        data class ActInfo(
            @SerializedName("icon_url")
            var iconUrl: String, // /operation/img/1478763176/1478762941492.png
            @SerializedName("name")
            var name: String, // 尚硅谷福利专区 黄金狗粮限量11.1元抢
            @SerializedName("url")
            var url: String // /oper/1478763176app.html
        )

        data class BannerInfo(
            @SerializedName("image")
            var image: String, // /1478770583836.png
            @SerializedName("option")
            var option: Int, // 1
            @SerializedName("type")
            var type: Int, // 0
            @SerializedName("value")
            var value: Value
        ) {
            data class Value(
                @SerializedName("url")
                var url: String // /act20161111?cyc_app=1
            )
        }

        data class ChannelInfo(
            @SerializedName("channel_name")
            var channelName: String, // 更多
            @SerializedName("image")
            var image: String, // /app/img/menu-more.png
            @SerializedName("option")
            var option: Int, // 6
            @SerializedName("type")
            var type: Int, // 1
            @SerializedName("value")
            var value: Value
        ) {
            data class Value(
                @SerializedName("channel_id")
                var channelId: String // 13
            )
        }

        data class HotInfo(
            @SerializedName("cover_price")
            var coverPrice: String, // 329.00
            @SerializedName("figure")
            var figure: String, // /supplier/1467702094592.jpg
            @SerializedName("name")
            var name: String, // 【wacom】数位板画板ctl471手绘板bamboo电脑绘画电子绘图板ps
            @SerializedName("product_id")
            var productId: String // 7752
        )

        data class RecommendInfo(
            @SerializedName("cover_price")
            var coverPrice: String, // 138.00
            @SerializedName("figure")
            var figure: String, // /1478849792177.jpg
            @SerializedName("name")
            var name: String, // 【尚硅谷】学院风 日常百搭 宽松长袖衬衫
            @SerializedName("product_id")
            var productId: String // 10654
        )

        data class SeckillInfo(
            @SerializedName("end_time")
            var endTime: String, // 1479052800
            @SerializedName("list")
            var list: List<X>,
            @SerializedName("start_time")
            var startTime: String // 1478772000
        ) {
            data class X(
                @SerializedName("cover_price")
                var coverPrice: String, // 49.00
                @SerializedName("figure")
                var figure: String, // /1438680345318.jpg
                @SerializedName("name")
                var name: String, // 【古风原创】 自动直柄伞 晴雨伞 【青竹词】包邮  新增折叠伞
                @SerializedName("origin_price")
                var originPrice: String, // 59.00
                @SerializedName("product_id")
                var productId: String // 555
            )
        }
    }
}