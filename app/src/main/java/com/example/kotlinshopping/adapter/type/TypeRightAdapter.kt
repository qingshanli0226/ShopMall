package com.example.kotlinshopping.adapter.type

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinshopping.Constants
import com.example.kotlinshopping.DensityUtil
import com.example.kotlinshopping.bean.*
import kotlinx.android.synthetic.main.item_hot_right.view.*

class TypeRightAdapter(var context: Context,list: List<TypeResult>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

     // 热卖
    val HOT = 0
     lateinit var child: List<Child>
    //普通的
    val ORDINARY = 1
    lateinit var hot: List<HotProduct>
    //当前的类型
    var currentType: Int = 0
    init {
        if (list!= null&& list.isNotEmpty()){
            child = list[0].child
        }else{
            hot = list[0].hot_product_list
        }
    }
    override fun getItemViewType(position: Int): Int {
        if (position == HOT)
        {
            currentType = HOT
        }else{
            currentType = ORDINARY
        }
        return currentType
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class HotViewHolderView(var context: Context, var data: List<HotProduct>, itemView: View):RecyclerView.ViewHolder(itemView
    ){
         var linear: LinearLayout
        init {
            linear = itemView.linear
        }

        fun setData(){
            for (i in 0..data.size-1)
            {
             var   lineLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                val myLiner = LinearLayout(context)
                lineLp.setMargins(
                    DensityUtil.dip2px(context, 5f),
                    0,
                    DensityUtil.dip2px(context, 5f),
                    DensityUtil.dip2px(context, 20f)
                )
                myLiner.orientation = LinearLayout.VERTICAL

                linear.addView(myLiner,lineLp)
                //设置图片
                var lp = LinearLayout.LayoutParams(
                    DensityUtil.dip2px(context, 80f),
                    DensityUtil.dip2px(context, 80f)
                )
                var imageView = ImageView(context)
                //请求图片
                Glide.with(context).load(Constants.BASE_URl_IMAGE+data[i].figure).into(imageView)
                //设置距离底部有10个dp
                lp.setMargins(0, 0, 0, DensityUtil.dip2px(context, 10f))

                myLiner.addView(imageView)

                //设置价格
                var textViewLp = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var textView = TextView(context)
                textView.text = "￥" +data.get(i).cover_price
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.parseColor("#ed3f3f"))

                myLiner.addView(textView,textViewLp)
                myLiner.setTag(i)

                //点击事件
//                myLiner.setOnClickListener{
//                    val i = myLiner.getTag() as Int
//
//                    val cover_price = data[i].cover_price
//                    val name = data[i].name()
//                    val figure = data[i].figure()
//                    val product_id = data[i].product_id()
//                    val goodsBean = GoodsBean(name, cover_price, figure, product_id)
//
//                    val intent = Intent(context, GoodsInfoActivity::class.java)
//                    intent.putExtra("goods_bean", goodsBean)
//                    context.startActivity(intent)
//                    // Toast.makeText(mContext, "position" + i, Toast.LENGTH_SHORT).show();
//                }
            }
        }
    }
}