package com.example.homework.type.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.homework.R
import com.example.homework.type.adapter.TypeLeftAdapter
import com.example.homework.type.bean.Result
import com.example.homework.type.bean.TypeBean
import com.example.homework.utils.Constants
import com.example.homework.utils_internet.Utils_Internet
import kotlinx.android.synthetic.main.fragment_list.*
import org.jetbrains.anko.support.v4.toast

class ListFragment : Fragment(), Utils_Internet.CallBackTypeData {

    private var isFirst = true
    private lateinit var result: List<Result>
    private lateinit var leftAdapter: TypeLeftAdapter
    private val urls = arrayOf<String>(
        Constants.SKIRT_URL,
        Constants.JACKET_URL,
        Constants.PANTS_URL,
        Constants.OVERCOAT_URL,
        Constants.ACCESSORY_URL,
        Constants.BAG_URL,
        Constants.DRESS_UP_URL,
        Constants.HOME_PRODUCTS_URL,
        Constants.STATIONERY_URL,
        Constants.DIGIT_URL,
        Constants.GAME_URL
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //数据请求
        getDataFromNet(urls[0])
    }

    //TODO 数据请求
    private fun getDataFromNet(url: String) {
        Utils_Internet.getTypeData(url, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }


    override fun onSuccess(typeBean: TypeBean) {
        if (typeBean != null) {
            println("${typeBean.toString()}")
            //解析数据
            result = typeBean.result

            if (isFirst){
                leftAdapter = TypeLeftAdapter(context!!.applicationContext)
                lv_left.adapter = leftAdapter
            }

            initListenter(leftAdapter)
            //解析右边数据




        }
    }

    fun initListenter(adapter: TypeLeftAdapter) {
        //点击监听
        lv_left.setOnItemClickListener { parent, view, position, id ->
            adapter.changeSelected(position) //刷新

            if (position != 0) {
                isFirst = false
            }

            getDataFromNet(urls[position])
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

    override fun onError(e: Throwable) {
        toast(e.printStackTrace().toString())
    }

}