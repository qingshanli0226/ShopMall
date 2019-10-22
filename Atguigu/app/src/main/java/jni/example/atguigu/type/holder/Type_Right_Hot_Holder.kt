package jni.example.atguigu.type.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jni.example.atguigu.type.Bean.HotProduct
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.item_ordinary_right.view.*

class Type_Right_Hot_Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(list: List<HotProduct>) {
        for (i in list) {
            Glide.with(itemView.context).load("${Constants.BASE_URl_IMAGE}${i.figure}").into(itemView.iv_ordinary_right)
            itemView.tv_ordinary_right.text = i.cover_price
        }
    }
}