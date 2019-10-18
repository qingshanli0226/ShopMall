package com.example.myshopping.type.fragment

import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment
import com.example.myshopping.type.adapter.MyFragAdapter
import com.flyco.tablayout.SegmentTabLayout
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.fragment_type.*

class TypeFragment : BaseFragment() {
    var fragList = mutableListOf<Fragment>()
    override fun initView(): View {
        val view = View.inflate(context, R.layout.fragment_type, null)
        var vp_type = view.findViewById<FrameLayout>(R.id.fl_type)
        var stl = view.findViewById<SegmentTabLayout>(R.id.stl)

        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fl_type,ListFragment(), "list")
        fragmentTransaction.add(R.id.fl_type,TagFragment(), "tag")
        fragmentTransaction.commit()

        stl.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                val fragmentManager = activity!!.supportFragmentManager
                val fragmentTransaction1 = fragmentManager.beginTransaction()
                when (position) {
                    0 -> {
                        fragmentTransaction1.hide(TagFragment())
                        fragmentTransaction1.show(ListFragment())
                    }
                    1 -> {
                        fragmentTransaction1.hide(ListFragment())
                        fragmentTransaction1.show(TagFragment())
                    }
                }
                fragmentTransaction1.commit()
            }

            override fun onTabReselect(position: Int) {

            }

        })

        val titles = arrayOf("分类", "标签")

        stl.setTabData(titles)

        return view
    }


}