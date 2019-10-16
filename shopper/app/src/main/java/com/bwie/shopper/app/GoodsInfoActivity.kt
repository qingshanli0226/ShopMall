package com.atguigu.shoppingmall.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast

import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide
import com.bwie.shopper.NumberAddSubView
import com.bwie.shopper.R
import com.bwie.shopper.ShoppingCartActivity
import com.bwie.shopper.app.CartProvider

/**
 * 商品信息列表
 */
class GoodsInfoActivity : Activity(), View.OnClickListener {
     var ibGoodInfoBack: ImageButton? = null
     var ibGoodInfoMore: ImageButton? = null
     var ivGoodInfoImage: ImageView? = null
     var tvGoodInfoName: TextView? = null
     var tvGoodInfoDesc: TextView? = null
     var tvGoodInfoPrice: TextView? = null
     var tvGoodInfoStore: TextView? = null
     var tvGoodInfoStyle: TextView? = null
     var wbGoodInfoMore: WebView? = null
     var tvGoodInfoCallcenter: TextView? = null
     var tvGoodInfoCollection: TextView? = null
     var tvGoodInfoCart: TextView? = null
     var btnGoodInfoAddcart: Button? = null
     var tvMoreShare: TextView? = null
     var tvMoreSearch: TextView? = null
     var tvMoreHome: TextView? = null
     var ll_root: LinearLayout? = null
     var btn_more: Button? = null

     var cartProvider: CartProvider? = null
    // private Boolean isFirst = true;

    /* //模拟商家的数组
     private String[] sellers = new String[]{"尚硅谷", "画影工作室", "Wacom"};
     private List<GoodsList> goodsLists;
    private GoodsList goodsList;*/
    private val goodsBeans: List<GoodsBean>? = null
    private var goods_bean: GoodsBean? = null

    /**
     * Find the Views in the layout<br></br>
     * <br></br>
     * Auto-created on 2016-10-09 01:34:23 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private fun findViews() {
        ibGoodInfoBack = findViewById<View>(R.id.ib_good_info_back) as ImageButton
        ibGoodInfoMore = findViewById<View>(R.id.ib_good_info_more) as ImageButton
        ivGoodInfoImage = findViewById<View>(R.id.iv_good_info_image) as ImageView
        tvGoodInfoName = findViewById<View>(R.id.tv_good_info_name) as TextView
        tvGoodInfoDesc = findViewById<View>(R.id.tv_good_info_desc) as TextView
        tvGoodInfoPrice = findViewById<View>(R.id.tv_good_info_price) as TextView
        tvGoodInfoStore = findViewById<View>(R.id.tv_good_info_store) as TextView
        tvGoodInfoStyle = findViewById<View>(R.id.tv_good_info_style) as TextView
        wbGoodInfoMore = findViewById<View>(R.id.wb_good_info_more) as WebView

        tvGoodInfoCallcenter = findViewById<View>(R.id.tv_good_info_callcenter) as TextView
        tvGoodInfoCollection = findViewById<View>(R.id.tv_good_info_collection) as TextView
        tvGoodInfoCart = findViewById<View>(R.id.tv_good_info_cart) as TextView
        btnGoodInfoAddcart = findViewById<View>(R.id.btn_good_info_addcart) as Button

        ll_root = findViewById<View>(R.id.ll_root) as LinearLayout
        tvMoreShare = findViewById<View>(R.id.tv_more_share) as TextView
        tvMoreSearch = findViewById<View>(R.id.tv_more_search) as TextView
        tvMoreHome = findViewById<View>(R.id.tv_more_home) as TextView

        btn_more = findViewById<View>(R.id.btn_more) as Button

        ibGoodInfoBack!!.setOnClickListener(this)
        ibGoodInfoMore!!.setOnClickListener(this)
        btnGoodInfoAddcart!!.setOnClickListener(this)

        tvMoreShare!!.setOnClickListener(this)
        tvMoreSearch!!.setOnClickListener(this)
        tvMoreHome!!.setOnClickListener(this)

        btn_more!!.setOnClickListener(this)

        tvGoodInfoCallcenter!!.setOnClickListener(this)
        tvGoodInfoCollection!!.setOnClickListener(this)
        tvGoodInfoCart!!.setOnClickListener(this)
        btnGoodInfoAddcart!!.setOnClickListener(this)
        tvGoodInfoCallcenter!!.setOnClickListener(this)
    }

    /**
     * Handle button click events<br></br>
     * <br></br>
     * Auto-created on 2016-10-09 01:34:23 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    override fun onClick(v: View) {
        if (v === ibGoodInfoBack) {
            finish()
        } else if (v === ibGoodInfoMore) {
            if (ll_root!!.visibility == View.VISIBLE) {
                ll_root!!.visibility = View.GONE
            } else {
                ll_root!!.visibility = View.VISIBLE
            }
        } else if (v === btn_more) {
            ll_root!!.visibility = View.GONE
        } else if (v === tvMoreShare) {
            Toast.makeText(this@GoodsInfoActivity, "分享", Toast.LENGTH_SHORT).show()
            //            showShare();
        } else if (v === tvMoreSearch) {
            Toast.makeText(this@GoodsInfoActivity, "搜索", Toast.LENGTH_SHORT).show()
        } else if (v === tvMoreHome) {
            Constants.isBackHome = true
            finish()
        } else if (v === tvGoodInfoCallcenter) {
            Toast.makeText(this@GoodsInfoActivity, "客服", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@GoodsInfoActivity, CallCenterActivity::class.java)
            startActivity(intent)
        } else if (v === tvGoodInfoCollection) {
            Toast.makeText(this@GoodsInfoActivity, "收藏", Toast.LENGTH_SHORT).show()
        } else if (v === tvGoodInfoCart) {
            //            Toast.makeText(GoodsInfoActivity.this, "购物车", Toast.LENGTH_SHORT).show();
            val intent = Intent(this, ShoppingCartActivity::class.java)
            startActivity(intent)

        } else if (v === btnGoodInfoAddcart) {
            //添加购物车
            //            cartProvider.addData(goods_bean);
            showPopwindow()
            //            Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_info)
        findViews()

        cartProvider = CartProvider.instance
        //取出intent
        val intent = intent
        goods_bean = intent.getSerializableExtra("goods_bean") as GoodsBean
        if (goods_bean != null) {
            //本地获取存储的数据
            setDataFormView(goods_bean!!)
        }

    }

    private fun setWebView(product_id: String?) {

        if (product_id != null) {
            //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
            //            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wbGoodInfoMore!!.loadUrl("http://www.atguigu.com")
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wbGoodInfoMore!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url)
                    return true
                }
            }
            //启用支持javascript
            val settings = wbGoodInfoMore!!.settings
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true

            //优先使用缓存
            wbGoodInfoMore!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
    }


    fun setDataFormView(goodsBean: GoodsBean) {
        val name = goodsBean.name
        val cover_price = goodsBean.cover_price
        val figure = goodsBean.figure
        val product_id = goodsBean.product_id

        Glide.with(this).load(Constants.BASE_URl_IMAGE + figure!!).into(ivGoodInfoImage!!)
        if (name != null) {
            tvGoodInfoName!!.text = name
        }
        if (cover_price != null) {
            tvGoodInfoPrice!!.text = "￥$cover_price"
        }
        setWebView(product_id)
    }


    /**
     * 显示popupWindow
     */
    private fun showPopwindow() {

        // 1 利用layoutInflater获得View
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popupwindow_add_product, null)

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        val window = PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)

        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.isFocusable = true

        // 实例化一个ColorDrawable颜色为半透明
        val dw = ColorDrawable(-0x1)
        window.setBackgroundDrawable(dw)

        // 设置popWindow的显示和消失动画
//        window.animationStyle = R.style.mypopwindow_anim_style


        // 4 控件处理
        val iv_goodinfo_photo = view.findViewById<View>(R.id.iv_goodinfo_photo) as ImageView
        val tv_goodinfo_name = view.findViewById<View>(R.id.tv_goodinfo_name) as TextView
        val tv_goodinfo_price = view.findViewById<View>(R.id.tv_goodinfo_price) as TextView
        val nas_goodinfo_num = view.findViewById<View>(R.id.nas_goodinfo_num) as NumberAddSubView
        val bt_goodinfo_cancel = view.findViewById<View>(R.id.bt_goodinfo_cancel) as Button
        val bt_goodinfo_confim = view.findViewById<View>(R.id.bt_goodinfo_confim) as Button

        // 加载图片
        Glide.with(this@GoodsInfoActivity).load(Constants.BASE_URl_IMAGE + goods_bean!!.figure!!).into(iv_goodinfo_photo)

        // 名称
        tv_goodinfo_name.text = goods_bean!!.name
        // 显示价格
        tv_goodinfo_price.text = goods_bean!!.cover_price

        // 设置最大值和当前值
        nas_goodinfo_num.maxValue = 8
        nas_goodinfo_num.value = 1

        nas_goodinfo_num.setOnNumberChangeListener(object : NumberAddSubView.OnNumberChangeListener {
            override fun addNumber(view: View, value: Int) {
                goods_bean!!.number = value
            }

            override fun subNumner(view: View, value: Int) {
                goods_bean!!.number = value
            }
        })

        bt_goodinfo_cancel.setOnClickListener { window.dismiss() }

        bt_goodinfo_confim.setOnClickListener {
            window.dismiss()
            //添加购物车
            cartProvider!!.addData(goods_bean!!)
            Log.e("TAG", "66:" + goods_bean!!.toString())
            Toast.makeText(this@GoodsInfoActivity, "添加购物车成功", Toast.LENGTH_SHORT).show()
        }

        window.setOnDismissListener { window.dismiss() }

        // 5 在底部显示
//        window.showAtLocation(this@GoodsInfoActivity.findViewById(R.id.ll_goods_root),
//                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(this@GoodsInfoActivity))
//
    }
}
