package jni.example.atguigu.utils

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService : Service() {

    inner class Mybind:Binder(){
        fun  getservice():MyService{
            return this@MyService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object{

    }
}
