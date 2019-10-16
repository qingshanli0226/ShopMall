package com.atguigu.shoppingmall.community.fragment



import android.annotation.SuppressLint
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageButton

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.community.adapter.CommunityViewPagerAdapter


@SuppressLint("ValidFragment")
class CommunityFragment(var mainActivity: MainActivity) : BaseFragment() {
    private var ibCommunityIcon: ImageButton? = null
    private var tablayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var ibCommunityMessage: ImageButton? = null

    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_community, null)
        ibCommunityIcon = view.findViewById<View>(R.id.ib_community_icon) as ImageButton
        tablayout = view.findViewById<View>(R.id.tablayout) as TabLayout
        viewPager = view.findViewById<View>(R.id.view_pager) as ViewPager
        ibCommunityMessage = view.findViewById<View>(R.id.ib_community_message) as ImageButton

        val adapter = CommunityViewPagerAdapter(fragmentManager,mainActivity)
        viewPager!!.adapter = adapter
        tablayout!!.visibility = View.VISIBLE
        tablayout!!.setupWithViewPager(viewPager)
        return view
    }

    override fun initData() {
        super.initData()
    }

}
