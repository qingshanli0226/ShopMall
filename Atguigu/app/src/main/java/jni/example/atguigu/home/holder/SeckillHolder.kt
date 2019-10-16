package jni.example.atguigu.home.holder

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.home.Bean.SeckillInfo
import jni.example.atguigu.home.adapter.SeckillAdapter
import kotlinx.android.synthetic.main.seckill_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class SeckillHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var handler =object : Handler(){
        override fun handleMessage(msg: Message) {
            if(msg.what==1){
                dt-=1000
                var sdf:SimpleDateFormat = SimpleDateFormat("HH:mm:ss")
                itemView.tv_time_seckill.text = sdf.format(Date(dt.toLong()))
            }
        }
    }
    var isBegin:Boolean = true
    var dt:Int = 0
    @SuppressLint("WrongConstant")
    fun setData(seckillInfo: SeckillInfo?){
        if(seckillInfo==null){
            return
        }
        if(isBegin){
            val startTime = seckillInfo.start_time
            val endTime = seckillInfo.end_time
            dt = endTime.toInt()-startTime.toInt()
            isBegin = false
        }

        handler.sendEmptyMessageDelayed(0,1000)
        val list = seckillInfo.list
        with(itemView){
            rv_seckill.layoutManager = LinearLayoutManager(itemView.context,LinearLayout.HORIZONTAL,false)
            rv_seckill.adapter = SeckillAdapter(list)
        }
    }
}