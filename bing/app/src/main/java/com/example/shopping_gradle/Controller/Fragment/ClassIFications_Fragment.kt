package com.example.shopping_gradle.Controller.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.Controller.Fragment.ClassTwoFragment.ListFragment
import com.example.shopping_gradle.Controller.Fragment.ClassTwoFragment.TagFragment
import com.example.shopping_gradle.R
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.class_frag.*
import kotlinx.android.synthetic.main.class_frag.view.*

class ClassIFications_Fragment: Fragment() {
        var fragmentList= mutableListOf<Fragment>()
        var listFragment= ListFragment()
        var tagFragment= TagFragment()
        var tempFragment:Fragment?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View= inflater.inflate(R.layout.class_frag,container,false)

        InitFragment()
        val titles:Array<String> = arrayOf("分类","标签")
        val type_Segment = view.Type_Segment
        type_Segment.setTabData(titles)
        type_Segment.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                Log.e("##S","分类")
                switchFragment(tempFragment,fragmentList.get(position))
            }

            override fun onTabReselect(position: Int) {
            }
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        switchFragment(tempFragment,fragmentList.get(0))
    }

    private fun InitFragment() {
        fragmentList.add(listFragment)
        fragmentList.add(tagFragment)
        switchFragment(tempFragment,fragmentList.get(0))
    }


   private fun switchFragment(fromFragment: Fragment?, nextFragment: Fragment?) {
        if (tempFragment !== nextFragment) {
            tempFragment = nextFragment
            if (nextFragment != null) {
                val transaction = activity!!.getSupportFragmentManager().beginTransaction()
                //判断nextFragment是否添加
                if (!nextFragment!!.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.add(R.id.Type_FramLayout, nextFragment!!, "tagFragment").commit()
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.show(nextFragment!!).commit()
                }
            }
        }
    }
//    private fun switchFragment(temp: Fragment?, nextfrag: Fragment ?) {
//
//        if(tempFragment!=nextfrag) {
//            tempFragment=nextfrag
//            //判断是否添加Fragment
//            if(nextfrag!=null){
//                val transaction = activity!!.supportFragmentManager.beginTransaction()
//                if(!!nextfrag.isAdded){
//                    //隐藏
//                    if(temp!=null){
//                        transaction.hide(temp)
//                    }
//                    transaction.add(R.id.Type_FramLayout,nextfrag,"tagFragment").commit()
//                }else{
//
//                    if(temp!=null){
//                        transaction.hide(temp)
//                    }
//                    transaction.show(nextfrag).commit()
//                }
//            }
//
//        }
//    }

}