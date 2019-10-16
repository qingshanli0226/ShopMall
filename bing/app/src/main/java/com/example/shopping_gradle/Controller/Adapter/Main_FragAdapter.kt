package com.example.shopping_gradle.Controller.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class Main_FragAdapter(fm: FragmentManager?,mlist:MutableList<Fragment>?) : FragmentPagerAdapter(fm) {

    var mlist2=mlist
    override fun getItem(p0: Int): Fragment {
        return mlist2!!.get(p0)
    }

    override fun getCount(): Int {
        return mlist2!!.size
    }
}