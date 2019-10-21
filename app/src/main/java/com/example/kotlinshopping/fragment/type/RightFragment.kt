package com.example.kotlinshopping.fragment.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.R
import com.example.kotlinshopping.adapter.type.TagAdapter
import com.example.kotlinshopping.bean.TagBean
import com.example.kotlinshopping.bean.TagResult
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.fragment_type.*

class RightFragment : Fragment(){

    var rootView:View? = null
    var gridView:GridView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView  = inflater.inflate(R.layout.fragment_tag,null)
        gridView = rootView!!.findViewById(R.id.gv_tag)
        loadClient()
        return rootView
    }

    private fun loadClient() {
        OkGo.get<String>(Constants.TAG_URL)
            .execute(object : StringCallback(){
                override fun onSuccess(response: Response<String>?) {
                    var json = response!!.body()
                    val fromJson = Gson().fromJson<TagResult>(json, TagBean::class.java)

                    //gridView.adapter = TagAdapter(context!!,fromJson)
                }

            })
    }


}