package jni.example.atguigu.type.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jni.example.atguigu.R
import jni.example.atguigu.type.Bean.Child
import jni.example.atguigu.type.Bean.HotProduct
import jni.example.atguigu.type.holder.Type_Right_Child_Holder
import jni.example.atguigu.type.holder.Type_Right_Hot_Holder

class Type_list_Right_Adapter(
    child:List<Child>,
    hot_product_list: List<HotProduct>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var child:List<Child> = child
    var hot_product_list:List<HotProduct> = hot_product_list

    //TODO 热卖
    val HOT = 0
    //TODO 普通的
    val ORDINARY = 1

    var currentType = 0

    override fun getItemViewType(position: Int): Int {
        when(position){
            HOT -> currentType = HOT
            ORDINARY ->currentType = ORDINARY
        }
        return currentType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            HOT -> Type_Right_Hot_Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_ordinary_right,parent,false))
            ORDINARY-> Type_Right_Child_Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_ordinary_right,parent,false))
            else ->Type_Right_Hot_Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_ordinary_right,parent,false))
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(position){
            HOT ->Type_Right_Hot_Holder(holder.itemView).setData(hot_product_list)
            ORDINARY ->Type_Right_Child_Holder(holder.itemView).setData(child)
        }
    }
}