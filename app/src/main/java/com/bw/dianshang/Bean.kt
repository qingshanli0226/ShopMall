package com.bw.dianshang

data class Bean(
    val code: Int,
    val msg: String,
    val result: Result
)

data class Result(
    val act_info: List<ActInfo>,
    val banner_info: List<BannerInfo>,
    val channel_info: List<ChannelInfo>,
    val hot_info: List<HotInfo>,
    val recommend_info: List<RecommendInfo>,
    val seckill_info: SeckillInfo
)

data class ActInfo(
    val icon_url: String,
    val name: String,
    val url: String
)

data class BannerInfo(
    val image: String,
    val option: Int,
    val type: Int,
    val value: Value
)

data class Value(
    val url: String
)

data class ChannelInfo(
    val channel_name: String,
    val image: String,
    val option: Int,
    val type: Int,
    val value: ValueX
)

data class ValueX(
    val channel_id: String
)

data class HotInfo(
    val cover_price: String,
    val figure: String,
    val name: String,
    val product_id: String
)

data class RecommendInfo(
    val cover_price: String,
    val figure: String,
    val name: String,
    val product_id: String
)

data class SeckillInfo(
    val end_time: String,
    val list: List<X>,
    val start_time: String
)

data class X(
    val cover_price: String,
    val figure: String,
    val name: String,
    val origin_price: String,
    val product_id: String
)