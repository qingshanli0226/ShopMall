package com.example.myshopping.home.bean

data class JsonBean(
    val code: Int,
    val msg: String,
    val result: List<Result>
)

data class Result(
    val child: List<Child>,
    val hot_product_list: List<HotProduct>,
    val is_deleted: String,
    val name: String,
    val p_catalog_id: String,
    val parent_id: String,
    val pic: String
)

data class Child(
    val is_deleted: String,
    val name: String,
    val p_catalog_id: String,
    val parent_id: String,
    val pic: String
)

data class HotProduct(
    val brand_id: String,
    val brief: String,
    val channel_id: String,
    val cover_price: String,
    val figure: String,
    val name: String,
    val p_catalog_id: String,
    val product_id: String,
    val sell_time_end: String,
    val sell_time_start: String,
    val supplier_code: String,
    val supplier_type: String
)