package jni.example.atguigu.home.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.home.Bean.HotInfo
import jni.example.atguigu.home.adapter.HotAdapter
import kotlinx.android.synthetic.main.hot_item.view.*

class HotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(hotInfo: List<HotInfo>?){
        if(hotInfo==null){
            return
        }
        with(itemView){
            gv_hot.adapter = HotAdapter(hotInfo)
        }
    }

}