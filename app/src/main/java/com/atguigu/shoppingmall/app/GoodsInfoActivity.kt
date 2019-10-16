package com.atguigu.shoppingmall.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
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

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.home.uitls.VirtualkeyboardHeight
import com.atguigu.shoppingmall.shoppingcart.activity.ShoppingCartActivity
import com.atguigu.shoppingmall.shoppingcart.utils.CartProvider
import com.atguigu.shoppingmall.shoppingcart.view.NumberAddSubView
import com.atguigu.shoppingmall.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_goods_info.*
import kotlinx.android.synthetic.main.more_layout.*

/**
 * 商品信息列表
 */
class GoodsInfoActivity : Activity(), View.OnClickListener {

    private var cartProvider: CartProvider? = null
    // private Boolean isFirst = true;

    /* //模拟商家的数组
     private String[] sellers = new String[]{"尚硅谷", "画影工作室", "Wacom"};
     private List<GoodsList> goodsLists;
    private GoodsList goodsList;*/
    private val goodsBeans: List<GoodsBean>? = null
    private var goods_bean: GoodsBean? = null


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

    private fun findViews() {

        tv_more_share!!.setOnClickListener(this)
        tv_more_search!!.setOnClickListener(this)
        tv_more_home!!.setOnClickListener(this)
        btn_more!!.setOnClickListener(this)
        ib_good_info_back!!.setOnClickListener(this)
        ib_good_info_more!!.setOnClickListener(this)
        btn_good_info_addcart!!.setOnClickListener(this)
        tv_good_info_callcenter!!.setOnClickListener(this)
        tv_good_info_collection!!.setOnClickListener(this)
        tv_good_info_cart!!.setOnClickListener(this)
        btn_good_info_addcart!!.setOnClickListener(this)
    }

    private fun setWebView(product_id: String?) {
        Log.e("xxx 网址 ","${product_id}")
        if (product_id != null) {
            //http://192.168.51.104:8080/atguigu/json/GOODSINFO_URL.json2691
            //            wbGoodInfoMore.loadUrl(Constants.GOODSINFO_URL + product_id);
            wb_good_info_more!!.loadUrl("http://www.atguigu.com")
            //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
            wb_good_info_more!!.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    Log.e("xxx 监听中的网址 ","${url}")

                    view.loadUrl(url)
                    if(url.startsWith("http:") || url.startsWith("https:") ) {

                    }else{
                        val str = url.substring(9, url.length)
                        Log.e("xxx 剪辑之后的网址 ","${str}")
                        view.loadUrl(str)

//                        val intent2 = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                        startActivity(intent2)
                    }

                    return true
                }
            }
            //启用支持javascript
            val settings = wb_good_info_more!!.settings
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true

            //优先使用缓存
            wb_good_info_more!!.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
    }


    fun setDataFormView(goodsBean: GoodsBean) {
        val name = goodsBean.name
        val cover_price = goodsBean.cover_price
        val figure = goodsBean.figure
        val product_id = goodsBean.product_id

        Glide.with(this).load(Constants.BASE_URl_IMAGE + figure).into(iv_good_info_image!!)
        tv_good_info_name!!.text = name
        tv_good_info_price!!.text = "￥$cover_price"
        setWebView(product_id)
    }


    /**
     * 显示popupWindow
     */
    private fun showPopwindow() {

        // 利用layoutInflater获得View
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.popupwindow_add_product, null)

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        val window = PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)

        // 3参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.isFocusable = true

        // 实例化一个ColorDrawable颜色为半透明
        val dw = ColorDrawable(-0x1)
        window.setBackgroundDrawable(dw)

        // 设置popWindow的显示和消失动画
        window.animationStyle = R.style.mypopwindow_anim_style


        // 控件处理
        val iv_goodinfo_photo:ImageView = view.findViewById(R.id.iv_goodinfo_photo)
        val tv_goodinfo_name :TextView= view.findViewById(R.id.tv_goodinfo_name)
        val tv_goodinfo_price:TextView = view.findViewById(R.id.tv_goodinfo_price)
        val nas_goodinfo_num:NumberAddSubView = view.findViewById(R.id.nas_goodinfo_num)
        val bt_goodinfo_cancel :Button= view.findViewById(R.id.bt_goodinfo_cancel)
        val bt_goodinfo_confim :Button= view.findViewById(R.id.bt_goodinfo_confim)

        // 加载图片
        Glide.with(this@GoodsInfoActivity)
                .load(Constants.BASE_URl_IMAGE + goods_bean!!.figure)
                .into(iv_goodinfo_photo)

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
        window.showAtLocation(this@GoodsInfoActivity.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(this@GoodsInfoActivity))

    }

    override fun onClick(v: View) {
        when(v){
            //返回
            ib_good_info_back-> finish()
            //右上角
            ib_good_info_more->{
                if (ll_root!!.visibility == View.VISIBLE) {
                    ll_root!!.visibility = View.GONE
                } else {
                    ll_root!!.visibility = View.VISIBLE
                }
            }
            btn_more-> ll_root!!.visibility = View.GONE
            tv_more_share->Toast.makeText(this@GoodsInfoActivity, "分享", Toast.LENGTH_SHORT).show()
            tv_more_search->Toast.makeText(this@GoodsInfoActivity, "搜索", Toast.LENGTH_SHORT).show()
            tv_good_info_collection->Toast.makeText(this@GoodsInfoActivity, "收藏", Toast.LENGTH_SHORT).show()
            tv_more_home-> {
                Constants.isBackHome = true
                finish()
            }
            tv_good_info_callcenter-> {
                Toast.makeText(this@GoodsInfoActivity, "客服", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@GoodsInfoActivity, CallCenterActivity::class.java)
                startActivity(intent)
            }
            tv_good_info_cart->{
                val intent = Intent(this, ShoppingCartActivity::class.java)
                startActivity(intent)
            }
            btn_good_info_addcart->showPopwindow()
        }
    }
}
