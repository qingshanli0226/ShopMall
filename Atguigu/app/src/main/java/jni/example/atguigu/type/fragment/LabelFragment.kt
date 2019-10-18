package jni.example.atguigu.type.fragment

import android.graphics.Color
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import jni.example.atguigu.R
import jni.example.atguigu.adapter.AllPowerfulAdapter
import jni.example.atguigu.base.BaseFragment
import jni.example.atguigu.type.Bean.Result
import jni.example.atguigu.type.Bean.TypeTabBean
import jni.example.atguigu.type.holder.TypeTabHolder
import jni.example.atguigu.utils.Constants
import jni.example.atguigu.utils.OkUtlis
import kotlinx.android.synthetic.main.fragment_type_label.view.*
import kotlinx.android.synthetic.main.item_type_tabel.view.*

class LabelFragment : BaseFragment() {
    var result:List<Result>? = null
    lateinit var adapter:AllPowerfulAdapter<Result,TypeTabHolder>
    private val colors = arrayOf(
        Color.parseColor("#f0a420"),
        Color.parseColor("#4ba5e2"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#4ba5e2"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#f0a420"),
        Color.parseColor("#f0839a"),
        Color.parseColor("#f0a420"),
        Color.parseColor("#4ba5e2")
    )
    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        var view = inflater.inflate(R.layout.fragment_type_label,null)
        result = ArrayList()
        adapter =object:AllPowerfulAdapter<Result,TypeTabHolder>(){
            override fun getType(position: Int): Int {
                return if(result?.size==null) 0 else result!!.size
            }

            override fun getViewHolder(parent: ViewGroup, viewType: Int): TypeTabHolder {
                return TypeTabHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_type_tabel,parent,false))
            }

            override fun setOnBind(holder: TypeTabHolder, position: Int) {
                with(holder.itemView){
                    tv_tag.text = result?.get(position)?.name
                    tv_tag.setTextColor(colors[position % colors.size])
                }
            }
        }
        view.type_label_rv.layoutManager = GridLayoutManager(context,3)
        view.type_label_rv.adapter = adapter
        onGetJson()
        return view
    }
    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            if(msg.what==100){
                val typeTabBean =
                    Gson().fromJson<TypeTabBean>(msg.obj.toString(), TypeTabBean::class.java)
                result = typeTabBean.result
                adapter.updateData(result as MutableList<Result>)
            }
        }
    }
    fun onGetJson(){
        OkUtlis.doGet("${Constants.BASE}${Constants.TAG_URL}",object : OkUtlis.MyCallback{
            override fun success(string: String) {
                val message = Message.obtain()
                message.obj=string
                message.what=100
                handler.handleMessage(message)
            }

            override fun error(error: String) {

            }
        })
    }

}