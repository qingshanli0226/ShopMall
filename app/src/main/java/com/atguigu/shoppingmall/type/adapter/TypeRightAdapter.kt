package com.atguigu.shoppingmall.type.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.GoodsInfoActivity
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.type.bean.TypeBean
import com.atguigu.shoppingmall.utils.Constants
import com.atguigu.shoppingmall.utils.DensityUtil
import com.bumptech.glide.Glide


class TypeRightAdapter(private val mContext: Context, result: List<TypeBean.ResultBean>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /**
     * 常用分类
     */
    private var child: List<TypeBean.ResultBean.ChildBean>? = null
    /**
     * 热卖商品列表的数据
     */
    private var hot_product_list: List<TypeBean.ResultBean.HotProductListBean>? = null


    /**
     * 当前的类型
     */
    var currentType: Int = 0

    private val mLayoutInflater: LayoutInflater

    init {

        mLayoutInflater = LayoutInflater.from(mContext)

        if (result != null && result.size > 0) {
            child = result[0].child
            hot_product_list = result[0].hot_product_list
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HOT) {
            HotViewHolder(mLayoutInflater.inflate(R.layout.item_hot_right, null), mContext)
        } else {
            OrdinaryViewHolder(mLayoutInflater.inflate(R.layout.item_ordinary_right, null), mContext)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HOT) {
            val hotViewHolder = holder as HotViewHolder
            hotViewHolder.setData(hot_product_list!!)
        } else {
            val ordinaryViewHolder = holder as OrdinaryViewHolder
            ordinaryViewHolder.setData(child!![position - 1], position - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == HOT) {
            currentType = HOT
        } else {
            currentType = ORDINARY
        }
        return currentType
    }

    override fun getItemCount(): Int {
        return child!!.size + 1
    }

    internal inner class OrdinaryViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private val iv_ordinary_right: ImageView
        private val tv_ordinary_right: TextView
        private val ll_root: LinearLayout

        init {
            iv_ordinary_right = itemView.findViewById<View>(R.id.iv_ordinary_right) as ImageView
            tv_ordinary_right = itemView.findViewById<View>(R.id.tv_ordinary_right) as TextView
            ll_root = itemView.findViewById<View>(R.id.ll_root) as LinearLayout


        }

        fun setData(childBean: TypeBean.ResultBean.ChildBean, position: Int) {
            //加载图片
            Glide.with(mContext)
                    .load(Constants.BASE_URl_IMAGE + childBean.pic!!)
                    .into(iv_ordinary_right)
            //设置名称
            tv_ordinary_right.text = childBean.name

            ll_root.setOnClickListener { Toast.makeText(mContext, "posotion$position", Toast.LENGTH_SHORT).show() }
        }
    }

    internal inner class HotViewHolder(itemView: View, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private val linear: LinearLayout

        init {
            linear = itemView.findViewById<View>(R.id.linear) as LinearLayout

        }

        fun setData(hot_product_list: List<TypeBean.ResultBean.HotProductListBean>) {
            for (i in hot_product_list.indices) {

                val lineLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val myLinear = LinearLayout(mContext)
                lineLp.setMargins(DensityUtil.dip2px(mContext, 5f), 0, DensityUtil.dip2px(mContext, 5f), DensityUtil.dip2px(mContext, 20f))
                myLinear.orientation = LinearLayout.VERTICAL


                //添加到孩子里面
                linear.addView(myLinear, lineLp)

                //设置图片
                val lp = LinearLayout.LayoutParams(DensityUtil.dip2px(mContext, 80f), DensityUtil.dip2px(mContext, 80f))
                val imageView = ImageView(mContext)
                //请求图片
                Glide.with(mContext)
                        .load(Constants.BASE_URl_IMAGE + hot_product_list[i].figure!!)
                        .into(imageView)
                //设置距离底部有10个dp
                lp.setMargins(0, 0, 0, DensityUtil.dip2px(mContext, 10f))

                myLinear.addView(imageView, lp)


                //设置价格
                val textViewLp = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val textView = TextView(mContext)
                textView.text = "￥" + hot_product_list[i].cover_price!!
                textView.gravity = Gravity.CENTER
                textView.setTextColor(Color.parseColor("#ed3f3f"))


                //添加到布局里面
                myLinear.addView(textView, textViewLp)


                myLinear.tag = i
                //点击事件
                myLinear.setOnClickListener {
                    val i = myLinear.tag as Int

                    val cover_price = hot_product_list[i].cover_price
                    val name = hot_product_list[i].name
                    val figure = hot_product_list[i].figure
                    val product_id = hot_product_list[i].product_id
                    val goodsBean = GoodsBean(name, cover_price, figure, product_id)

                    val intent = Intent(mContext, GoodsInfoActivity::class.java)
                    intent.putExtra("goods_bean", goodsBean)
                    mContext.startActivity(intent)
                    // Toast.makeText(mContext, "position" + i, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    companion object {

        /**
         * 热卖
         */
        val HOT = 0
        /**
         * 普通的
         */
        val ORDINARY = 1
    }


}
