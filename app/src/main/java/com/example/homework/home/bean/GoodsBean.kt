package com.example.homework.home.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoodsBean(
    val name:String,
    val cover_price:String,
    val figure:String,
    val product_id:String,
    var number:Int = 1,
    var isEditing:Boolean = false,
    var isChildSelected:Boolean = false
) : Parcelable{}