package com.example.shoppingapplication.user.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppingapplication.R

class UserFragment:Fragment (){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = View.inflate(context, R.layout.fragment_user,null)

        return v
    }
}