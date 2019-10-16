package com.example.shoppingapplication.community.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.example.shoppingapplication.R

class HotPostFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = View.inflate(context, R.layout.fragment_hot_post,null)
        return v
    }
}