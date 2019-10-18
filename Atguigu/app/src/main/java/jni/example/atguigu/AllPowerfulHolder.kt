package jni.example.atguigu

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open abstract class AllPowerfulHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun <T> setDate(date:T)
}