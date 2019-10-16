package com.example.administrator.shoppingproject.Adpater

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

class FramentAdpater : FragmentPagerAdapter {
    internal lateinit var arr: ArrayList<Fragment>
    internal lateinit var text: Context


    constructor(fm: FragmentManager, arr: ArrayList<Fragment>, text: Context) : super(fm) {
        this.arr = arr
        this.text = text
    }
    constructor(fm: FragmentManager) : super(fm){

    }


    override fun getItem(i: Int): Fragment {
        return arr[i]
    }

    override fun getCount(): Int {
        return arr.size
    }
}
