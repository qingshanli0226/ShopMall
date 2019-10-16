package jni.example.atguigu.home.holder

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.home.Bean.RecommendInfo
import jni.example.atguigu.home.Bean.SeckillInfo
import jni.example.atguigu.home.adapter.RecommendAdapter
import jni.example.atguigu.home.adapter.SeckillAdapter
import kotlinx.android.synthetic.main.recommend_item.view.*
import kotlinx.android.synthetic.main.seckill_item.view.*

class RecommendHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(recommendInfo: List<RecommendInfo>?){
        if(recommendInfo==null){
            return
        }
        with(itemView){
            gv_recommend.adapter=RecommendAdapter(recommendInfo)
        }
    }
}