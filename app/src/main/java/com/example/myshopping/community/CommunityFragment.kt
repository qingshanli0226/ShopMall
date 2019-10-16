package com.example.myshopping.community

import android.view.View
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment

class CommunityFragment :BaseFragment() {
    override fun initView(): View {
        val view = View.inflate(context, R.layout.fragment_community, null)
        return view
    }
}