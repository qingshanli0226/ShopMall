package jni.example.atguigu.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.HotInfo
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.item_hot_grid_view.view.*

class HotAdapter (
    hotInfo: List<HotInfo>
): BaseAdapter() {
    var hotInfo:List<HotInfo>? = null
    init {
        this.hotInfo = hotInfo
    }
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        lateinit var view: View
        lateinit var viewholder:ViewHolder
        if(p1==null){
            view = LayoutInflater.from(p2!!.context).inflate(R.layout.item_hot_grid_view,p2,false)
            viewholder = ViewHolder(view)
            view.setTag(viewholder)
        }else{
            view = p1
            viewholder = view.getTag() as ViewHolder
        }
        Glide.with(p2!!.context).load("${Constants.BASE_URl_IMAGE}${hotInfo?.get(p0)?.figure}").into(viewholder.iv_hot)
        viewholder.tv_name.text = hotInfo?.get(p0)?.name
        viewholder.tv_price.text = "${hotInfo?.get(p0)?.cover_price}￥"
        return view
    }

    override fun getItem(p0: Int): Any {
        return hotInfo!!.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return hotInfo!!.size
    }

    class ViewHolder(itemView: View){
        var tv_price = itemView.tv_price
        var iv_hot = itemView.iv_hot
        var tv_name = itemView.tv_name
    }
}