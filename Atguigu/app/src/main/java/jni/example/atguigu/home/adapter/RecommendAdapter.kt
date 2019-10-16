package jni.example.atguigu.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import jni.example.atguigu.R
import jni.example.atguigu.home.Bean.RecommendInfo
import jni.example.atguigu.utils.Constants
import kotlinx.android.synthetic.main.item_recommend_grid_view.view.*

class RecommendAdapter(
    recommendInfo:List<RecommendInfo>
) : BaseAdapter() {
    var recommendInfo:List<RecommendInfo>? = null
    init {
        this.recommendInfo = recommendInfo
    }
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        lateinit var views:View
        lateinit var holder:ViewHolder
        if(view==null){
            views = LayoutInflater.from(parent!!.context).inflate(R.layout.item_recommend_grid_view,parent,false)
            holder = ViewHolder(views)
            views.setTag(holder)
        }else{
            views = view
            holder = views.getTag() as ViewHolder
        }
        Glide.with(parent!!.context).load("${Constants.BASE_URl_IMAGE}${recommendInfo!!.get(position).figure}").into(holder.iv_recommend)
        holder.tv_name.text = recommendInfo?.get(position)?.name
        holder.tv_price.text = "${recommendInfo?.get(position)?.cover_price}￥"
        return views
    }

    override fun getItem(position: Int): Any {
        return recommendInfo!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recommendInfo!!.size
    }

    inner class ViewHolder(itemView: View){
        var iv_recommend = itemView.iv_recommend
        var tv_name = itemView.tv_name
        var tv_price = itemView.tv_price
    }
}