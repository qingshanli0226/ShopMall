package com.example.homework1.Utils

import android.os.Handler
import android.os.Message

class HttpThread : Thread {

    private var url : String
    private var handler : Handler

    constructor(url: String, handler: Handler) : super() {
        this.url = url
        this.handler = handler
    }


    override fun run() {
        super.run()
        println("进入")
        val http = Utils.startHttp(url) ?: "null"
        val message = Message.obtain()
        message.what = 100
        message.obj = http
        handler.sendMessage(message)
    }
}