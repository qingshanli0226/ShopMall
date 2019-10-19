package com.example.homework.type.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.homework.R
import com.example.homework.type.adapter.TypeLeftAdapter
import com.example.homework.type.beantag.Result
import com.example.homework.type.beantag.TagBean
import com.example.homework.utils.Constants
import com.example.homework.utils_internet.Utils_Internet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.toast

class TagFragment : Fragment() {

    private var isFirst = true
    private var result = mutableListOf<Result>()
    private lateinit var leftAdapter: TypeLeftAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //数据请求
        getDataFromNet()
    }

    //TODO 数据请求
    private fun getDataFromNet() {
        Utils_Internet.getTagData(Constants.TAG_URL, object :Utils_Internet.CallBackTagTypeData{
            override fun onSuccess(tagBean: TagBean) {
                println(tagBean.toString())
                val json = Gson().fromJson(tagBean.result.toString(), Result::class.java)
                println(json.toString())

            }

            override fun onError(e: Throwable) {
                toast(e.localizedMessage)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }


    fun initListenter(adapter: TypeLeftAdapter) {
        //点击监听
        lv_left.setOnItemClickListener { parent, view, position, id ->
            adapter.changeSelected(position) //刷新

            if (position != 0) {
                isFirst = false
            }


            leftAdapter.notifyDataSetChanged()
        }

        //选中监听
        lv_left.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                adapter.changeSelected(position)
            }
        }
    }

}