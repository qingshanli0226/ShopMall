package com.example.homework1.Utils

import java.net.HttpURLConnection
import java.net.URL

class HttpUtils{

    fun getHttp(URL: String) : String?{

        var url:URL = URL(URL)
        println("开始下载数据$url")
        var huc : HttpURLConnection = url.openConnection() as HttpURLConnection
        huc.requestMethod = "GET"
        huc.connect()
        if(huc.responseCode==200){
            println("网络连接成功")
            val inputStream = huc.inputStream
            val byteArray = ByteArray(1024*1000)
            var len : Int = -1
            var json : StringBuffer = StringBuffer()
            val now = System.currentTimeMillis()
            while ({len = inputStream.read(byteArray);len}() !=  -1){
                json.append(String(byteArray,0,len))
            }
            println("成功耗时${(System.currentTimeMillis()-now)/1000}秒")
            return json.toString()
        }else{
            println("网络连接失败")
            return null
        }
    }
}
