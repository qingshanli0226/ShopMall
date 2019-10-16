package com.example.myshopping.shoppingcart

import android.view.View
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment

class ShoppingCartFragment : BaseFragment() {
    override fun initView(): View {
        val view = View.inflate(context, R.layout.fragment_shopping_cart, null)
        return view
    }
}