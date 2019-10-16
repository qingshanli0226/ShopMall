package com.example.homework1.Utils

object Utils {
    val httpUtils : HttpUtils = HttpUtils()

    fun startHttp(URL : String) : String?{
        println("进入2")
        val http = httpUtils.getHttp(URL)
        return http
    }
}