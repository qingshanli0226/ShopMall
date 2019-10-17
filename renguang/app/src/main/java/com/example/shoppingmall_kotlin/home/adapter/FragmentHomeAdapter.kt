package com.example.shoppingmall_kotlin.home.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import com.example.shoppingmall_kotlin.R
import com.example.shoppingmall_kotlin.home.fragment.*

import java.util.ArrayList

class FragmentHomeAdapter : FragmentPagerAdapter {

    var fragments: MutableList<Fragment> = arrayListOf()

    constructor(fm:FragmentManager) :super(fm){
        fragments.add(HomeFragment())
        fragments.add(TypeFragment())
        fragments.add(CommunityFragment())
        fragments.add(CartFragment())
        fragments.add(UserFragment())
    }

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}
