package jni.example.atguigu.type

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flyco.tablayout.listener.OnTabSelectListener
import jni.example.atguigu.R
import jni.example.atguigu.base.BaseFragment
import jni.example.atguigu.type.fragment.LabelFragment
import jni.example.atguigu.type.fragment.Type_List_Fragment
import kotlinx.android.synthetic.main.fragment_type.*

class TypeFragment : BaseFragment() {
    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.fragment_type, null)
        return view
    }

    override fun initDate() {
        super.initDate()
        var array:Array<String> = arrayOf("分类","标签")
        segment_tab.setTabData(array)

        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_type,Type_List_Fragment()).commit()
        segment_tab.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
                Log.d("lhf",position.toString())
                when(position){
                    0->activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_type,Type_List_Fragment()).commit()
                    1->activity!!.supportFragmentManager.beginTransaction().replace(R.id.fl_type,LabelFragment()).commit()
                }
            }

            override fun onTabReselect(position: Int) {

            }
        })
    }




}