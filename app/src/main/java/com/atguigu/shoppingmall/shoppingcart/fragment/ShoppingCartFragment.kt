package com.atguigu.shoppingmall.shoppingcart.fragment


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.alipay.sdk.app.PayTask
import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.shoppingcart.adapter.ShopCartAdapter
import com.atguigu.shoppingmall.shoppingcart.utils.CartProvider
import com.atguigu.shoppingmall.shoppingcart.utils.PayKeys
import com.atguigu.shoppingmall.utils.Constants

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

@SuppressLint("ValidFragment")
class ShoppingCartFragment(var mainActivity: MainActivity) : BaseFragment() {

    private var tvShopcartEdit: TextView? = null
    private var recyclerview: RecyclerView? = null
    private var llCheckAll: LinearLayout? = null
    private var checkboxAll: CheckBox? = null
    private lateinit var tvShopcartTotal: TextView
    private var btnCheckOut: Button? = null
    private var llDelete: LinearLayout? = null
    private lateinit var cbAll: CheckBox
    private var btnDelete: Button? = null
    private var btnCollection: Button? = null
    private var ivEmpty: ImageView? = null
    private var tvEmptyCartTobuy: TextView? = null
    private var ll_empty_shopcart: LinearLayout? = null

    private var adapter: ShopCartAdapter? = null


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

            }
        }
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private val outTradeNo: String
        get() {
            val format = SimpleDateFormat("MMddHHmmss", Locale.getDefault())
            val date = Date()
            var key = format.format(date)

            val r = Random()
            key = key + r.nextInt()
            key = key.substring(0, 15)
            return key
        }

    /**
     * get the sign type we use. 获取签名方式
     */
    private val signType: String
        get() = "sign_type=\"RSA\""


    private fun findViews(view: View) {
        tvShopcartEdit = view.findViewById<View>(R.id.tv_shopcart_edit) as TextView
        recyclerview = view.findViewById<View>(R.id.recyclerview) as RecyclerView
        llCheckAll = view.findViewById<View>(R.id.ll_check_all) as LinearLayout
        checkboxAll = view.findViewById<View>(R.id.checkbox_all) as CheckBox
        tvShopcartTotal = view.findViewById<View>(R.id.tv_shopcart_total) as TextView
        btnCheckOut = view.findViewById<View>(R.id.btn_check_out) as Button
        llDelete = view.findViewById<View>(R.id.ll_delete) as LinearLayout
        cbAll = view.findViewById<View>(R.id.cb_all) as CheckBox
        btnDelete = view.findViewById<View>(R.id.btn_delete) as Button
        btnCollection = view.findViewById<View>(R.id.btn_collection) as Button
        ivEmpty = view.findViewById<View>(R.id.iv_empty) as ImageView
        tvEmptyCartTobuy = view.findViewById<View>(R.id.tv_empty_cart_tobuy) as TextView
        ll_empty_shopcart = view.findViewById<View>(R.id.ll_empty_shopcart) as LinearLayout
        tvEmptyCartTobuy!!.isClickable = true
    }


    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_shoppingcart, null)
        findViews(view)
        return view
    }

    override fun initData() {
        initListener()
        tvShopcartEdit!!.tag = ACTION_EDIT
        tvShopcartEdit!!.text = "编辑"
        llCheckAll!!.visibility = View.VISIBLE
        showData()

    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    private fun initListener() {
        btnCheckOut!!.setOnClickListener { pay() }
        tvShopcartEdit!!.setOnClickListener {
            //设置编辑的点击事件
            val tag = tvShopcartEdit!!.tag as Int
            if (tag == ACTION_EDIT) {
                //变成完成状态
                showDelete()
            } else {
                //变成编辑状态
                hideDelete()
            }
        }
        btnDelete!!.setOnClickListener {
            adapter!!.deleteData()
            adapter!!.showTotalPrice()
            //显示空空如也
            checkData()
            adapter!!.checkAll()
        }
        tvEmptyCartTobuy!!.setOnClickListener {
            val intent = Intent(mainActivity, MainActivity::class.java)
            startActivity(intent)
            Constants.isBackHome = true
        }

    }

    private fun hideDelete() {
        tvShopcartEdit!!.text = "编辑"
        tvShopcartEdit!!.tag = ACTION_EDIT

        adapter!!.checkAll_none(true)
        llDelete!!.visibility = View.GONE
        llCheckAll!!.visibility = View.VISIBLE

        adapter!!.showTotalPrice()
    }

    private fun showDelete() {
        tvShopcartEdit!!.text = "完成"
        tvShopcartEdit!!.tag = ACTION_COMPLETE

        adapter!!.checkAll_none(false)
        cbAll.isChecked = false
        checkboxAll!!.isChecked = false

        llDelete!!.visibility = View.VISIBLE
        llCheckAll!!.visibility = View.GONE

        adapter!!.showTotalPrice()
    }

    private fun checkData() {
        if (adapter != null && adapter!!.itemCount > 0) {
            tvShopcartEdit!!.visibility = View.VISIBLE
            ll_empty_shopcart!!.visibility = View.GONE

        } else {
            ll_empty_shopcart!!.visibility = View.VISIBLE
            tvShopcartEdit!!.visibility = View.GONE

        }
    }

    private fun showData() {
        val cartProvider = CartProvider.instance

        val datas = cartProvider?.dataFromLocal
        if (datas != null && datas.size > 0) {
            tvShopcartEdit!!.visibility = View.VISIBLE

            adapter = ShopCartAdapter(mainActivity, datas, tvShopcartTotal, cartProvider, checkboxAll!!, cbAll)
            recyclerview!!.layoutManager = LinearLayoutManager(mainActivity)
            recyclerview!!.adapter = adapter
            ll_empty_shopcart!!.visibility = View.GONE
        } else {
            //显示空的
            tvShopcartEdit!!.visibility = View.GONE
            ll_empty_shopcart!!.visibility = View.VISIBLE


        }

    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    fun pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            AlertDialog.Builder(mainActivity).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定") { _, _ ->
                        //
                        //                            finish();
                    }.show()
            return
        }
        val orderInfo = getOrderInfo("淘宝购物", "爆发了...", tvShopcartTotal.text.toString().replace("￥", ""))//总价格

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        var sign = sign()
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        val payInfo = "$orderInfo&sign=\"$sign\"&$signType"

        val payRunnable = Runnable {
            // 构造PayTask 对象
            val alipay = PayTask(mainActivity as Activity)
            // 调用支付接口，获取支付结果
            val result = alipay.pay(payInfo, true)

            val msg = Message()
            msg.what = SDK_PAY_FLAG
            msg.obj = result
            mHandler.sendMessage(msg)
        }

        // 必须异步调用
        val payThread = Thread(payRunnable)
        payThread.start()
    }


    /**
     * create the order info. 创建订单信息
     */
    private fun getOrderInfo(subject: String, body: String, price: String): String {

        // 签约合作者身份ID
        var orderInfo = "partner=\"$PARTNER\""

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=\"$SELLER\""

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=\"$outTradeNo\""

        // 商品名称
        orderInfo += "&subject=\"$subject\""

        // 商品详情
        orderInfo += "&body=\"$body\""

        // 商品金额
        orderInfo += "&total_fee=\"$price\""

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\""

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\""

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\""

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\""

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\""

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\""

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private fun sign(): String? {
        return null
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

        //-------------支付------

        // 商户PID
        val PARTNER = PayKeys.DEFAULT_PARTNER   //这几个用了PayKey中的方法；
        // 商户收款账号
        val SELLER = PayKeys.DEFAULT_SELLER
        // 商户私钥，pkcs8格式
        val RSA_PRIVATE = PayKeys.PRIVATE
        // 支付宝公钥
        val RSA_PUBLIC = PayKeys.PUBLIC
        private val SDK_PAY_FLAG = 1
    }


}
