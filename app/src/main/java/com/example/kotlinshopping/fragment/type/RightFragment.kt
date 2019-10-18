package com.example.kotlinshopping.fragment.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinshopping.R
import kotlinx.android.synthetic.main.fragment_type.*

class RightFragment : Fragment(){

    var rootView:View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView  = inflater.inflate(R.layout.fragment_tag,null)
        return rootView
    }



}