package com.example.myshopping.type.fragment

import android.view.View
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment

class TypeFragment : BaseFragment() {
    override fun initView(): View {
        val view = View.inflate(context, R.layout.fragment_type, null)

        return view
    }


}