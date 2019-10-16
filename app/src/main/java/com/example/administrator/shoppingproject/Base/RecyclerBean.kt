package com.example.administrator.shoppingproject.Base

data class RecyclerBean(
    val `data`: List<Data>,
    val ret: Int
)

data class Data(
    val collect_num: String,
    val food_str: String,
    val id: String,
    val num: Int,
    val pic: String,
    val title: String
)