package com.example.homework.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework.R
import com.example.homework.type.fragment.ListFragment
import com.example.homework.type.fragment.TagFragment
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.fragment_type.view.*

class TypeFragment : Fragment() {
    private lateinit var fragmentList: ArrayList<Fragment>
    private lateinit var listFragment: ListFragment
    private lateinit var tagFragment: TagFragment
    private var currentFragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化一些数据
        fragmentList = arrayListOf<Fragment>()
        listFragment = ListFragment()
        tagFragment = TagFragment()

        fragmentList.add(listFragment)
        fragmentList.add(tagFragment)

        switchFragment(fragmentList[0])
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_type, container, false)

        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val titles = arrayOf("分类", "标签")

        view.segmentTabLayout.setTabData(titles)
        view.segmentTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {

                switchFragment(fragmentList[position])

            }

            override fun onTabReselect(position: Int) {

            }
        })
    }

    override fun onResume() {
        super.onResume()
        switchFragment(fragmentList[0])
    }

    fun switchFragment(fragment: Fragment) {
        if (currentFragment != fragment) {
            val transaction = activity!!.supportFragmentManager.beginTransaction()

            if (!fragment.isAdded) {
                if (currentFragment != null) {
                    transaction.hide(currentFragment)
                }
                transaction.add(R.id.fl_type, fragment).commit()
            } else {
                transaction.hide(currentFragment).show(fragment).commit()
            }

            currentFragment = fragment
        }
    }
}