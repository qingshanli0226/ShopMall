package com.bwie.shopper.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide
import com.bwie.shopper.NumberAddSubView
import com.bwie.shopper.R
import com.bwie.shopper.app.CartProvider

class ShopCartAdapter (private val mContext: Context, private val datas: MutableList<GoodsBean>, private val tvShopcartTotal: TextView, private val cartProvider: CartProvider, var checkboxAll: CheckBox, var cb_all: CheckBox) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val totalPrice: Double
        get() {
            var total = 0.0
            if (datas != null && datas.size > 0) {
                for (i in datas.indices) {
                    val goodsBean = datas[i]
                    if (goodsBean.isChildSelected)
                        total += goodsBean.cover_price?.let { java.lang.Double.parseDouble(it) }!! * java.lang.Double.parseDouble(goodsBean.number.toString() + "")
                }
            }
            return total
        }

    //回调点击事件的监听
    private var onItemClickListener: OnItemClickListener? = null

    init {

        //首次加载数据
        showTotalPrice()
        checkboxAll.setChecked(true)
        for (i in datas.indices) {
            datas.get(i).isChildSelected = true
        }
        showTotalPrice()

        setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClickListener(view: View, position: Int) {
                val goodsBean = datas.get(position)
                goodsBean.isChildSelected = !goodsBean.isChildSelected
                notifyItemChanged(position)
                checkAll()
                showTotalPrice()
            }
        })

        //设置全选点击事件
        checkboxAll.setOnClickListener(View.OnClickListener {
            val checked = checkboxAll!!.isChecked
            checkAll_none(checked)
            showTotalPrice()
        })

        cb_all.setOnClickListener(View.OnClickListener {
            val checked = cb_all!!.isChecked
            checkAll_none(checked)
            showTotalPrice()
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(View.inflate(mContext, R.layout.item_shop_cart, null))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        viewHolder.setData(datas!![position])

    }

    override fun getItemCount(): Int {
        return datas!!.size
    }

    fun checkAll_none(checked: Boolean) {
        if (datas != null && datas.size > 0) {
            for (i in datas.indices) {
                datas[i].isChildSelected = checked
                checkboxAll!!.isChecked = checked
                notifyItemChanged(i)
            }
        } else {
            checkboxAll!!.isChecked = false

        }
    }

    fun deleteData() {
        if (datas != null && datas.size > 0) {
            val iterator = datas.iterator()
            while (iterator.hasNext()) {

                val cart = iterator.next()

                if (cart.isChildSelected) {

                    //这行代码放在前面
                    val position = datas.indexOf(cart)
                    //1.删除本地缓存的
                    cartProvider.deleteData(cart)

                    //2.删除当前内存的
                    iterator.remove()

                    //3.刷新数据
                    notifyItemRemoved(position)

                }
            }
        }
    }

    fun checkAll() {
        if (datas != null && datas.size > 0) {
            for (i in datas.indices) {
                if (!datas[i].isChildSelected) {
                    checkboxAll!!.isChecked = false
                    cb_all!!.isChecked = false
                    return
                } else {
                    checkboxAll!!.isChecked = true
                    cb_all!!.isChecked = true
                }
            }
        }
    }


    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cbGov: CheckBox
        private val ivGov: ImageView
        private val tvDescGov: TextView
        private val tvPriceGov: TextView
        private val numberAddSubView: NumberAddSubView

        init {
            cbGov = itemView.findViewById<View>(R.id.cb_gov) as CheckBox
            ivGov = itemView.findViewById<View>(R.id.iv_gov) as ImageView
            tvDescGov = itemView.findViewById<View>(R.id.tv_desc_gov) as TextView
            tvPriceGov = itemView.findViewById<View>(R.id.tv_price_gov) as TextView
            numberAddSubView = itemView.findViewById<View>(R.id.numberAddSubView) as NumberAddSubView

            itemView.setOnClickListener { v ->
                if (onItemClickListener != null) {
                    onItemClickListener!!.onItemClickListener(v, layoutPosition)
                }
            }
        }

        fun setData(goodsBean: GoodsBean) {
            cbGov.isChecked = goodsBean.isChildSelected
            Glide.with(mContext)
                .load(Constants.BASE_URl_IMAGE + goodsBean.figure!!)
                .into(ivGov)
            tvDescGov.text = goodsBean.name
            tvPriceGov.text = "￥" + goodsBean.cover_price

            //设置数字加减回调
            numberAddSubView.value = goodsBean.number

            //-------------------------------------------
            //cartProvider = new CartProvider(mContext);

            numberAddSubView.setOnNumberChangeListener(object : NumberAddSubView.OnNumberChangeListener {
                override fun addNumber(view: View, value: Int) {
                    goodsBean.number = value
                    cartProvider.updataData(goodsBean)
                    showTotalPrice()
                }

                override fun subNumner(view: View, value: Int) {
                    goodsBean.number = value
                    cartProvider.updataData(goodsBean)
                    showTotalPrice()
                }
            })
        }
    }

    fun showTotalPrice() {
        tvShopcartTotal.text = totalPrice.toString() + ""
    }

    interface OnItemClickListener {
        fun onItemClickListener(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}