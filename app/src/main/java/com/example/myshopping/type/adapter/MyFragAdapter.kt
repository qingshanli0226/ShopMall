package com.example.myshopping.type.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyFragAdapter(var listFrag: MutableList<Fragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return listFrag.get(position)
    }

    override fun getCount(): Int {
        return listFrag.size
    }
}