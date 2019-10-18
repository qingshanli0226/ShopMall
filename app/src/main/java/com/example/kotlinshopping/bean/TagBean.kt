package com.example.kotlinshopping.bean

data class TagBean(
    val code: Int,
    val msg: String,
    val result: List<Result>
)

data class TagResult(
    val name: String,
    val tag_id: String
)