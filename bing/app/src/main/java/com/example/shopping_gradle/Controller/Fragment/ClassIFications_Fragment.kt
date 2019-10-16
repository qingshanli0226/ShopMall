package com.example.shopping_gradle.Controller.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopping_gradle.R

class ClassIFications_Fragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View= inflater.inflate(R.layout.class_frag,container,false)
        return view
    }
}