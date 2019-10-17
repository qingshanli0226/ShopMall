package jni.example.atguigu.home.Bean
import com.google.gson.annotations.SerializedName


data class GoodsBean(


    @SerializedName("code")
    var code: Int,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("result")
    var result: Result
) {
    data class Result(
        var type: Int,
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
            var iconUrl: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("url")
            var url: String
        )

        data class BannerInfo(
            @SerializedName("image")
            var image: String,
            @SerializedName("option")
            var option: Int,
            @SerializedName("type")
            var type: Int,
            @SerializedName("value")
            var value: Value
        ) {
            data class Value(
                @SerializedName("url")
                var url: String
            )
        }

        data class ChannelInfo(
            @SerializedName("channel_name")
            var channelName: String,
            @SerializedName("image")
            var image: String,
            @SerializedName("option")
            var option: Int,
            @SerializedName("type")
            var type: Int,
            @SerializedName("value")
            var value: Value
        ) {
            data class Value(
                @SerializedName("channel_id")
                var channelId: String
            )
        }

        data class HotInfo(
            @SerializedName("cover_price")
            var coverPrice: String,
            @SerializedName("figure")
            var figure: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("product_id")
            var productId: String
        )

        data class RecommendInfo(
            @SerializedName("cover_price")
            var coverPrice: String,
            @SerializedName("figure")
            var figure: String,
            @SerializedName("name")
            var name: String,
            @SerializedName("product_id")
            var productId: String
        )

        data class SeckillInfo(
            @SerializedName("end_time")
            var endTime: String,
            @SerializedName("list")
            var list: List<X>,
            @SerializedName("start_time")
            var startTime: String
        ) {
            data class X(
                @SerializedName("cover_price")
                var coverPrice: String,
                @SerializedName("figure")
                var figure: String,
                @SerializedName("name")
                var name: String,
                @SerializedName("origin_price")
                var originPrice: String,
                @SerializedName("product_id")
                var productId: String
            )
        }
    }
}