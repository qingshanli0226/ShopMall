package jni.example.atguigu.type.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class Type_PageAdapter(
    fm: FragmentManager,
    list: List<Fragment>
) : FragmentPagerAdapter(fm) {
    var list:List<Fragment>? = null
    var array:Array<String> = arrayOf("分类","标签")
    override fun getItem(position: Int): Fragment {
        return list!!.get(position)
    }

    override fun getCount(): Int {
        return list!!.size
    }
}