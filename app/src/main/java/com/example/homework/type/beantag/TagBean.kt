package com.example.homework.type.beantag

import com.example.homework.type.bean.Result

data class TagBean(
    val code: Int,
    val msg: String,
    val result: List<Result>
)
data class Result(
    val name: String,
    val tag_id: String
)
