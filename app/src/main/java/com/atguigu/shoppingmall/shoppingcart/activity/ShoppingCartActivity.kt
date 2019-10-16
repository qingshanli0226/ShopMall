package com.atguigu.shoppingmall.shoppingcart.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.shoppingcart.adapter.ShopCartAdapter
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.shoppingcart.utils.CartProvider


class ShoppingCartActivity : Activity(), View.OnClickListener {
    private var ibShopcartBack: ImageButton? = null
    private var tvShopcartEdit: TextView? = null
    private var recyclerview: RecyclerView? = null
    private var checkboxAll: CheckBox? = null
    private lateinit var tvShopcartTotal: TextView
    private var ll_check_all: LinearLayout? = null
    private var ll_delete: LinearLayout? = null
    private lateinit var cb_all: CheckBox
    private var btn_delete: Button? = null
    private var btn_collection: Button? = null
    private var btnCheckOut: Button? = null
    private var adapter: ShopCartAdapter? = null
    private var ll_empty_shopcart: LinearLayout? = null
    private var tv_empty_cart_tobuy: TextView? = null

    /**
     * Find the Views in the layout<br></br>
     * <br></br>
     * Auto-created on 2016-10-11 21:08:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private fun findViews() {
        ibShopcartBack = findViewById<View>(R.id.ib_shopcart_back) as ImageButton
        tvShopcartEdit = findViewById<View>(R.id.tv_shopcart_edit) as TextView
        recyclerview = findViewById<View>(R.id.recyclerview) as RecyclerView
        checkboxAll = findViewById<View>(R.id.checkbox_all) as CheckBox
        tvShopcartTotal = findViewById<View>(R.id.tv_shopcart_total) as TextView
        btnCheckOut = findViewById<View>(R.id.btn_check_out) as Button
        ll_check_all = findViewById<View>(R.id.ll_check_all) as LinearLayout
        ll_delete = findViewById<View>(R.id.ll_delete) as LinearLayout
        cb_all = findViewById<View>(R.id.cb_all) as CheckBox
        btn_delete = findViewById<View>(R.id.btn_delete) as Button
        btn_collection = findViewById<View>(R.id.btn_collection) as Button
        ll_empty_shopcart = findViewById<View>(R.id.ll_empty_shopcart) as LinearLayout
        tv_empty_cart_tobuy = findViewById<View>(R.id.tv_empty_cart_tobuy) as TextView

        ibShopcartBack!!.setOnClickListener(this)
        btnCheckOut!!.setOnClickListener(this)
        tvShopcartEdit!!.setOnClickListener(this)
        btn_delete!!.setOnClickListener(this)
        tv_empty_cart_tobuy!!.isClickable = true
        tv_empty_cart_tobuy!!.setOnClickListener(this)
    }

    /**
     * Handle button click events<br></br>
     * <br></br>
     * Auto-created on 2016-10-11 21:08:02 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    override fun onClick(v: View) {
        if (v === ibShopcartBack) {
            finish()
        } else if (v === btnCheckOut) {
            Toast.makeText(this@ShoppingCartActivity, "结算", Toast.LENGTH_SHORT).show()
        } else if (v === tvShopcartEdit) {
            //设置编辑的点击事件
            val tag = tvShopcartEdit!!.tag as Int
            if (tag == ACTION_EDIT) {
                //变成完成状态
                showDelete()
            } else {
                //变成编辑状态
                hideDelete()
            }
        } else if (v === btn_delete) {
            adapter!!.deleteData()
            adapter!!.showTotalPrice()
            //显示空空如也
            checkData()
            adapter!!.checkAll()
        } else if (v === tv_empty_cart_tobuy) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun hideDelete() {
        tvShopcartEdit!!.text = "编辑"
        tvShopcartEdit!!.tag = ACTION_EDIT

        adapter!!.checkAll_none(true)
        ll_delete!!.visibility = View.GONE
        ll_check_all!!.visibility = View.VISIBLE

        adapter!!.showTotalPrice()
    }

    private fun showDelete() {
        tvShopcartEdit!!.text = "完成"
        tvShopcartEdit!!.tag = ACTION_COMPLETE

        adapter!!.checkAll_none(false)
        cb_all.isChecked = false
        checkboxAll!!.isChecked = false

        ll_delete!!.visibility = View.VISIBLE
        ll_check_all!!.visibility = View.GONE

        adapter!!.showTotalPrice()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        findViews()

        showData()
        tvShopcartEdit!!.tag = ACTION_EDIT
        tvShopcartEdit!!.text = "编辑"

    }

    //-----------------------------------------
    private fun checkData() {
        if (adapter != null && adapter!!.itemCount > 0) {
            tvShopcartEdit!!.visibility = View.VISIBLE
            ll_empty_shopcart!!.visibility = View.GONE
            ll_check_all!!.visibility = View.GONE
        } else {
            ll_empty_shopcart!!.visibility = View.VISIBLE
            tvShopcartEdit!!.visibility = View.GONE
            ll_check_all!!.visibility = View.GONE
            ll_delete!!.visibility = View.GONE
        }
    }

    private fun showData() {
        val cartProvider = CartProvider.instance

        val datas:MutableList<GoodsBean>? = cartProvider?.dataFromLocal
        if (datas != null && datas.size > 0) {
            tvShopcartEdit!!.visibility = View.VISIBLE
            ll_empty_shopcart!!.visibility = View.GONE
            adapter = ShopCartAdapter(this, datas, tvShopcartTotal, cartProvider, checkboxAll!!, cb_all)
            recyclerview!!.layoutManager = LinearLayoutManager(this)
            recyclerview!!.adapter = adapter
        } else {
            //显示空的
            tvShopcartEdit!!.visibility = View.GONE
            ll_empty_shopcart!!.visibility = View.VISIBLE
            ll_check_all!!.visibility = View.GONE
            ll_delete!!.visibility = View.GONE
        }

    }

    companion object {
        /**
         * 编辑状态
         */
        private val ACTION_EDIT = 0
        /**
         * 完成状态
         */
        private val ACTION_COMPLETE = 1
    }
}
