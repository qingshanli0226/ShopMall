package jni.example.atguigu.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flyco.tablayout.listener.OnTabSelectListener
import jni.example.atguigu.R
import jni.example.atguigu.utils.Constants
import jni.example.atguigu.utils.OkUtlis
import kotlinx.android.synthetic.main.fragment_community.view.*
import kotlinx.android.synthetic.main.fragment_type.*
import kotlinx.android.synthetic.main.fragment_type.view.*

class TypeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_type, null)
        onGetJson()
        var list = arrayOf("分类","标签")
        segmenttable.setTabData(list)
        segmenttable.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabSelect(position: Int) {
            }

            override fun onTabReselect(position: Int) {

            }
        })
        return view
    }
    fun onGetJson(){
        OkUtlis.doGet(Constants.TAG_URL,object :OkUtlis.MyCallback{
            override fun success(string: String) {

            }

            override fun error(error: String) {

            }
        })
    }
}