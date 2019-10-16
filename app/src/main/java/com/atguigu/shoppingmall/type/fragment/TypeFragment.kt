package com.atguigu.shoppingmall.type.fragment


import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.base.BaseFragment
import com.flyco.tablayout.SegmentTabLayout
import com.flyco.tablayout.listener.OnTabSelectListener

import java.util.ArrayList

@SuppressLint("ValidFragment")
class TypeFragment(var mainActivity: MainActivity) : BaseFragment() {
    private var segmentTabLayout: SegmentTabLayout? = null
    private var iv_type_search: ImageView? = null
    private var fl_type: FrameLayout? = null
    private var fragmentList: MutableList<BaseFragment>? = null
    private var tempFragment: Fragment? = null
    lateinit var listFragment: ListFragment
    lateinit var tagFragment: TagFragment

    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_type, null)
        segmentTabLayout = view.findViewById<View>(R.id.tl_1) as SegmentTabLayout
        iv_type_search = view.findViewById<View>(R.id.iv_type_search) as ImageView
        fl_type = view.findViewById<View>(R.id.fl_type) as FrameLayout

        return view

    }

    override fun initData() {
        super.initData()

        initFragment()

        val titles = arrayOf("分类", "标签")

        segmentTabLayout!!.setTabData(titles)

        segmentTabLayout!!.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                switchFragment(tempFragment, fragmentList!![position])
            }

            override fun onTabReselect(position: Int) {

            }
        })

    }


    override fun onResume() {
        super.onResume()
        switchFragment(tempFragment, fragmentList!![0])
    }

    fun switchFragment(fromFragment: Fragment?, nextFragment: BaseFragment?) {
        if (tempFragment !== nextFragment) {
            tempFragment = nextFragment
            if (nextFragment != null) {
                val transaction = activity!!.supportFragmentManager.beginTransaction()
                //判断nextFragment是否添加
                if (!nextFragment.isAdded) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }

                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit()
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.show(nextFragment).commit()
                }
            }
        }
    }

    private fun initFragment() {
        fragmentList = ArrayList()
        listFragment = ListFragment(mainActivity)
        tagFragment = TagFragment(mainActivity)

        fragmentList!!.add(listFragment)
        fragmentList!!.add(tagFragment)

        switchFragment(tempFragment, fragmentList!![0])
    }


}
