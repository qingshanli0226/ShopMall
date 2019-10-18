package com.example.kotlinshopping.fragment.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.kotlinshopping.R

//首页Fragment
class SortFragment : Fragment() {

    var leftFregment: LeftFregment =
        LeftFregment()
    var rightFragment: RightFragment =
        RightFragment()
    var rootView:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_type,container,false)
        initFragment()
       selectBtn()

        return rootView
    }

    private fun initFragment() {
        var fragmentManager =  activity!!.supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.fl_type,leftFregment,"f1")
        fragmentManager.add(R.id.fl_type,rightFragment,"f2")
        fragmentManager.hide(rightFragment)
        fragmentManager.commit()
    }

    private fun selectBtn() {
        rootView!!.findViewById<RadioGroup>(R.id.mGroup)
            .setOnCheckedChangeListener { group, checkedId ->
                var fragmentManager = activity!!.supportFragmentManager.beginTransaction()
                when (checkedId) {
                    R.id.leftRB -> {
                        fragmentManager.hide(rightFragment)
                        fragmentManager.show(leftFregment)
                        fragmentManager.commit()
                    }
                    R.id.rightRB -> {
                        fragmentManager.hide(leftFregment)
                        fragmentManager.show(rightFragment)
                        fragmentManager.commit()
                    }
                }
            }


    }
    override fun onResume() {
        super.onResume()
    }
}