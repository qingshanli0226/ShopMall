package com.bwie.shopper.app

import android.content.Context
import android.text.TextUtils
import android.util.SparseArray
import com.atguigu.shoppingmall.app.MyAppliction
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.utils.CacheUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartProvider  private constructor(private val context: Context) {
    //优化过的HashMap集合
    private val datas: SparseArray<GoodsBean>?

    val allData: List<GoodsBean>?
        get() = dataFromLocal

    //本地获取json数据，并且通过Gson解析成list列表数据
    //从本地获取缓存数据
    //把数据转换成列表
    val dataFromLocal: MutableList<GoodsBean>
        get() {
            var carts: MutableList<GoodsBean> = ArrayList()
            val savaJson = CacheUtils.getString(context, JSON_CART)
            if (!TextUtils.isEmpty(savaJson)) {
                carts = Gson().fromJson(savaJson, object : TypeToken<List<GoodsBean>>() {

                }.type)
            }
            return carts

        }

    init {
        datas = SparseArray(100)
        listToSparse()
    }

    private fun listToSparse() {
        val carts = allData
        //放到sparseArry中
        if (carts != null && carts.size > 0) {
            for (i in carts.indices) {
                val goodsBean = carts[i]
                datas!!.put(Integer.parseInt(goodsBean.product_id!!), goodsBean)
            }
        }
    }


    private fun parsesToList(): List<GoodsBean> {
        val carts = ArrayList<GoodsBean>()
        if (datas != null && datas.size() > 0) {
            for (i in 0 until datas.size()) {
                val shoppingCart = datas.valueAt(i)
                carts.add(shoppingCart)
            }
        }
        return carts
    }

    fun addData(cart: GoodsBean) {

        //添加数据
        var tempCart: GoodsBean? = datas!!.get(Integer.parseInt(cart.product_id!!))
        if (tempCart != null) {
            tempCart.number = tempCart.number + cart.number
        } else {
            tempCart = cart
            tempCart.number = 1
        }

        datas.put(Integer.parseInt(tempCart.product_id!!), tempCart)


        //保存数据
        commit()
    }

    //保存数据
    private fun commit() {
        //把parseArray转换成list
        val carts = parsesToList()
        //把转换成String
        val json = Gson().toJson(carts)

        // 保存
        CacheUtils.putString(context, JSON_CART, json)

    }


    fun deleteData(cart: GoodsBean) {

        //删除数据

        datas!!.delete(Integer.parseInt(cart.product_id!!))


        //保存数据
        commit()
    }

    fun updataData(cart: GoodsBean) {
        //修改数据
        datas!!.put(Integer.parseInt(cart.product_id!!), cart)
        //保存数据
        commit()
    }

    /**
     * 根据key查找书籍
     * @param goods_bean
     * @return
     */
    fun findData(goods_bean: GoodsBean): GoodsBean? {
        val goodsBean = datas!!.get(Integer.parseInt(goods_bean.product_id!!))
        return if (goodsBean != null) {
            goods_bean
        } else null
    }

    companion object {
        val JSON_CART = "json_cart"

        private var cartProvider: CartProvider?=null

        val instance: CartProvider?
            get() {
                if (cartProvider == null) {
                    cartProvider = CartProvider(MyAppliction.context)
                }
                return cartProvider
            }
    }
}