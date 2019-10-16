package com.atguigu.shoppingmall.community.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.atguigu.shoppingmall.app.MainActivity

import com.atguigu.shoppingmall.community.fragment.HotPostFragment
import com.atguigu.shoppingmall.community.fragment.NewPostFragment

import java.util.ArrayList


class CommunityViewPagerAdapter(fm: FragmentManager?,var mainActivity: MainActivity) : FragmentPagerAdapter(fm) {
    private val fragmentList = ArrayList<Fragment>()
    private val titles = arrayOf("新帖", "热帖")

    init {
        initFragments()
    }

    private fun initFragments() {
        val newPostFragment = NewPostFragment(mainActivity)
        val hotPostFragment = HotPostFragment(mainActivity)
        fragmentList.add(newPostFragment)
        fragmentList.add(hotPostFragment)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}
