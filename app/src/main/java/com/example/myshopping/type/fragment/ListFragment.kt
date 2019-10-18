package com.example.myshopping.type.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myshopping.R
import com.example.myshopping.type.adapter.MyTypeListAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(context, R.layout.fragment_list, null)
        var lv_type = view.findViewById<ListView>(R.id.lv_type)
        val myTypeListAdapter = MyTypeListAdapter(activity!!)
        lv_type.adapter = myTypeListAdapter
        return view
    }


}