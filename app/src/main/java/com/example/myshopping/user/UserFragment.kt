package com.example.myshopping.user

import android.view.View
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment

class UserFragment :BaseFragment() {
    override fun initView(): View {
        val view = View.inflate(context, R.layout.fragment_user, null)
        return view
    }
}