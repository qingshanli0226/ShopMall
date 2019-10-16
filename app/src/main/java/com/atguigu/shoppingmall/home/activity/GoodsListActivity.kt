package com.atguigu.shoppingmall.home.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.GoodsInfoActivity
import com.atguigu.shoppingmall.home.adapter.ExpandableListViewAdapter
import com.atguigu.shoppingmall.home.adapter.GoodsListAdapter
import com.atguigu.shoppingmall.home.bean.GoodsBean
import com.atguigu.shoppingmall.home.bean.TypeListBean
import com.atguigu.shoppingmall.home.uitls.SpaceItemDecoration
import com.atguigu.shoppingmall.utils.Constants
import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback

import java.util.ArrayList

import okhttp3.Call
import okhttp3.Request

class GoodsListActivity : Activity(), View.OnClickListener {

    private var llGoodsListHead: LinearLayout? = null
    private var ibGoodsListBack: ImageButton? = null
    private var tvGoodsListSearch: TextView? = null
    private var ibGoodsListHome: ImageButton? = null
    private var tvGoodsListSort: TextView? = null
    private var llGoodsListPrice: LinearLayout? = null
    private var tvGoodsListPrice: TextView? = null
    private var ivGoodsListArrow: ImageView? = null
    private var tvGoodsListSelect: TextView? = null
    private var recyclerview: RecyclerView? = null
    private var listView: ExpandableListView? = null

    /*    private static final int DEFAULE_STATE = 1;
        private static final int ASC_STATE = 2;
        private static final int DESC_STATE = 3;*/
    private val childP: Int = 0
    private val groupP: Int = 0

    private var click_count = 0
    private lateinit var group: ArrayList<String>
    private lateinit var child: ArrayList<List<String>>
    private var adapter: ExpandableListViewAdapter? = null

    private var ll_select_root: LinearLayout? = null
    private var ll_price_root: LinearLayout? = null
    private var ll_theme_root: LinearLayout? = null
    private var ll_type_root: LinearLayout? = null

    private var ib_drawer_layout_back: ImageButton? = null
    private var btn_drawer_layout_confirm: Button? = null
    private var btn_drawer_layout_cancel: Button? = null

    private var btn_drawer_type_confirm: Button? = null
    private var btn_drawer_type_cancel: Button? = null

    private var btn_drawer_theme_confirm: Button? = null
    private var btn_drawer_theme_cancel: Button? = null

    private var rl_select_price: RelativeLayout? = null
    private var rl_select_recommend_theme: RelativeLayout? = null
    private var rl_select_type: RelativeLayout? = null
    private var rl_price_30_50: RelativeLayout? = null
    private var rl_theme_note: RelativeLayout? = null
    private var position: Int = 0
    private val urls = arrayOf(Constants.CLOSE_STORE, Constants.GAME_STORE, Constants.COMIC_STORE, Constants.COSPLAY_STORE, Constants.GUFENG_STORE, Constants.STICK_STORE, Constants.WENJU_STORE, Constants.FOOD_STORE, Constants.SHOUSHI_STORE)
    private lateinit var page_data: List<TypeListBean.ResultBean.PageDataBean>
    private var adapter1: GoodsListAdapter? = null
    private var dl_left: DrawerLayout? = null

    private fun findViews() {
        llGoodsListHead = findViewById<View>(R.id.ll_goods_list_head) as LinearLayout
        ibGoodsListBack = findViewById<View>(R.id.ib_goods_list_back) as ImageButton
        tvGoodsListSearch = findViewById<View>(R.id.tv_goods_list_search) as TextView
        ibGoodsListHome = findViewById<View>(R.id.ib_goods_list_home) as ImageButton
        tvGoodsListSort = findViewById<View>(R.id.tv_goods_list_sort) as TextView
        llGoodsListPrice = findViewById<View>(R.id.ll_goods_list_price) as LinearLayout
        tvGoodsListPrice = findViewById<View>(R.id.tv_goods_list_price) as TextView
        ivGoodsListArrow = findViewById<View>(R.id.iv_goods_list_arrow) as ImageView
        tvGoodsListSelect = findViewById<View>(R.id.tv_goods_list_select) as TextView
        recyclerview = findViewById<View>(R.id.recyclerview) as RecyclerView

        ll_select_root = findViewById<View>(R.id.ll_select_root) as LinearLayout
        ll_price_root = findViewById<View>(R.id.ll_price_root) as LinearLayout
        ll_theme_root = findViewById<View>(R.id.ll_theme_root) as LinearLayout
        ll_type_root = findViewById<View>(R.id.ll_type_root) as LinearLayout

        ib_drawer_layout_back = findViewById<View>(R.id.ib_drawer_layout_back) as ImageButton
        btn_drawer_layout_confirm = findViewById<View>(R.id.btn_drawer_layout_confirm) as Button
        btn_drawer_layout_cancel = findViewById<View>(R.id.btn_drawer_layout_cancel) as Button
        btn_drawer_type_confirm = findViewById<View>(R.id.btn_drawer_type_confirm) as Button
        btn_drawer_type_cancel = findViewById<View>(R.id.btn_drawer_type_cancel) as Button
        btn_drawer_theme_confirm = findViewById<View>(R.id.btn_drawer_theme_confirm) as Button
        btn_drawer_theme_cancel = findViewById<View>(R.id.btn_drawer_theme_cancel) as Button

        rl_select_price = findViewById<View>(R.id.rl_select_price) as RelativeLayout
        rl_select_recommend_theme = findViewById<View>(R.id.rl_select_recommend_theme) as RelativeLayout
        rl_select_type = findViewById<View>(R.id.rl_select_type) as RelativeLayout
        rl_price_30_50 = findViewById<View>(R.id.rl_price_30_50) as RelativeLayout
        rl_theme_note = findViewById<View>(R.id.rl_theme_note) as RelativeLayout
        dl_left = findViewById<View>(R.id.dl_left) as DrawerLayout

        ibGoodsListBack!!.setOnClickListener(this)
        ibGoodsListHome!!.setOnClickListener(this)
        tvGoodsListSearch!!.setOnClickListener(this)
        llGoodsListPrice!!.setOnClickListener(this)
        tvGoodsListSort!!.setOnClickListener(this)
        tvGoodsListSelect!!.setOnClickListener(this)
        ib_drawer_layout_back!!.setOnClickListener(this)

        rl_select_price!!.setOnClickListener(this)
        rl_select_recommend_theme!!.setOnClickListener(this)
        rl_select_type!!.setOnClickListener(this)

        btn_drawer_layout_confirm!!.setOnClickListener(this)
        btn_drawer_layout_cancel!!.setOnClickListener(this)
        btn_drawer_type_confirm!!.setOnClickListener(this)
        btn_drawer_type_cancel!!.setOnClickListener(this)
        btn_drawer_theme_confirm!!.setOnClickListener(this)
        btn_drawer_theme_cancel!!.setOnClickListener(this)

        rl_price_30_50!!.setOnClickListener(this)
        rl_theme_note!!.setOnClickListener(this)

        listView = findViewById<View>(R.id.expandableListView) as ExpandableListView

    }


    override fun onClick(v: View) {
        if (v === ibGoodsListBack) {
            finish()
        } else if (v === ibGoodsListHome) {
            //            Intent intent = new Intent(this, MainActivity.class);
            //            startActivity(intent);
            Constants.isBackHome = true
            finish()
        } else if (v === tvGoodsListSearch) {
            Toast.makeText(this@GoodsListActivity, "搜索", Toast.LENGTH_SHORT).show()
        } else if (v === llGoodsListPrice) {
            //价格点击事件
            click_count++
            //综合排序变为默认
            tvGoodsListSort!!.setTextColor(Color.parseColor("#333538"))
            //价格红
            tvGoodsListPrice!!.setTextColor(Color.parseColor("#ed4141"))
            if (click_count % 2 == 1) {
                // 箭头向下红
                ivGoodsListArrow!!.setBackgroundResource(R.drawable.new_price_sort_desc)
            } else {
                // 箭头向上红
                ivGoodsListArrow!!.setBackgroundResource(R.drawable.new_price_sort_asc)
            }
        } else if (v === tvGoodsListSort) {
            //综合排序点击事件
            click_count = 0
            ivGoodsListArrow!!.setBackgroundResource(R.drawable.new_price_sort_normal)
            tvGoodsListPrice!!.setTextColor(Color.parseColor("#333538"))
            tvGoodsListSort!!.setTextColor(Color.parseColor("#ed4141"))
        } else if (v === tvGoodsListSelect) {
            //筛选的点击事件
            tvGoodsListSelect!!.setTextColor(Color.parseColor("#ed4141"))
            dl_left!!.openDrawer(Gravity.RIGHT)

        } else if (v === rl_select_price) {
            //价格筛选的页面
            ll_price_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.GONE

            showPriceLayout()
        } else if (v === rl_select_recommend_theme) {
            ll_theme_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.GONE

            showThemeLayout()
        } else if (v === rl_select_type) {
            ll_type_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.GONE

            showTypeLayout()
        } else if (v === ib_drawer_layout_back) {
            dl_left!!.closeDrawers()
        } else if (v === btn_drawer_layout_cancel) {
            Toast.makeText(this@GoodsListActivity, "取消", Toast.LENGTH_SHORT).show()

            ll_select_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.VISIBLE
            showSelectorLayout()
        } else if (v === btn_drawer_layout_confirm) {
            Toast.makeText(this@GoodsListActivity, "确认", Toast.LENGTH_SHORT).show()
        } else if (v === rl_price_30_50) {
            Toast.makeText(this@GoodsListActivity, "123123123", Toast.LENGTH_SHORT).show()
        } else if (v === rl_theme_note) {
            Toast.makeText(this@GoodsListActivity, "123123123", Toast.LENGTH_SHORT).show()
        } else if (v === btn_drawer_type_confirm) {
            Toast.makeText(this@GoodsListActivity, "确认", Toast.LENGTH_SHORT).show()
        } else if (v === btn_drawer_type_cancel) {
            Toast.makeText(this@GoodsListActivity, "取消", Toast.LENGTH_SHORT).show()
            ll_select_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.VISIBLE
            showSelectorLayout()
        } else if (v === btn_drawer_theme_confirm) {
            Toast.makeText(this@GoodsListActivity, "确认", Toast.LENGTH_SHORT).show()
        } else if (v === btn_drawer_theme_cancel) {
            ll_select_root!!.visibility = View.VISIBLE
            ib_drawer_layout_back!!.visibility = View.VISIBLE
            showSelectorLayout()
        }


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_list)
        findViews()

        val intent = intent
        position = intent.getIntExtra("position", -1)

        getDataFromNet()
        ll_select_root!!.visibility = View.VISIBLE
        ib_drawer_layout_back!!.visibility = View.VISIBLE
        showSelectorLayout()

        initListener()
    }

    private fun initListener() {
        /* listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(GoodsListActivity.this, "childPosition" + childPosition, Toast.LENGTH_SHORT).show();
                childP = childPosition;
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(GoodsListActivity.this, "groupPosition" + groupPosition, Toast.LENGTH_SHORT).show();
                groupP = groupPosition;
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GoodsListActivity.this, "position---" + position, Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    //筛选页面
    private fun showSelectorLayout() {
        ll_price_root!!.visibility = View.GONE
        ll_theme_root!!.visibility = View.GONE
        ll_type_root!!.visibility = View.GONE
    }

    //价格页面
    private fun showPriceLayout() {
        ll_select_root!!.visibility = View.GONE
        ll_theme_root!!.visibility = View.GONE
        ll_type_root!!.visibility = View.GONE
    }

    //主题页面
    private fun showThemeLayout() {
        ll_select_root!!.visibility = View.GONE
        ll_price_root!!.visibility = View.GONE
        ll_type_root!!.visibility = View.GONE
    }

    //类别页面
    private fun showTypeLayout() {
        ll_select_root!!.visibility = View.GONE
        ll_price_root!!.visibility = View.GONE
        ll_theme_root!!.visibility = View.GONE

        //初始化ExpandableListView
        initExpandableListView()
        adapter = ExpandableListViewAdapter(this, group, child)
        listView!!.setAdapter(adapter)
    }

    private fun initExpandableListView() {
        group = ArrayList()
        child = ArrayList()
        //去掉默认箭头
        listView!!.setGroupIndicator(null)
        addInfo("全部", arrayOf())
        addInfo("上衣", arrayOf("古风", "和风", "lolita", "日常"))
        addInfo("下装", arrayOf("日常", "泳衣", "汉风", "lolita", "创意T恤"))
        addInfo("外套", arrayOf("汉风", "古风", "lolita", "胖次", "南瓜裤", "日常"))

        // 这里是控制如果列表没有孩子菜单不展开的效果
        listView!!.setOnGroupClickListener { parent, v, groupPosition, id ->
            if (child!![groupPosition].isEmpty()) {// isEmpty没有
                true
            } else {
                false
            }
        }
    }

    /**
     * 添加数据信息
     *
     * @param g
     * @param c
     */
    private fun addInfo(g: String, c: Array<String>) {
        group!!.add(g)
        val list = ArrayList<String>()
        for (i in c.indices) {
            list.add(c[i])
        }
        child!!.add(list)
    }


    fun getDataFromNet() {
        OkHttpUtils
                .get()
                .url(urls[position])
                .id(100)
                .build()
                .execute(MyStringCallback())
    }

    inner class MyStringCallback : StringCallback() {


        override fun onBefore(request: Request, id: Int) {}

        override fun onAfter(id: Int) {}

        override fun onError(call: Call, e: Exception, id: Int) {
            Log.e("TAG", "联网失败" + e.message)
        }

        override fun onResponse(response: String?, id: Int) {

            when (id) {
                100 ->
                    //                    Toast.makeText(GoodsListActivity.this, "http", Toast.LENGTH_SHORT).show();
                    if (response != null) {
                        processData(response)
                        val manager = GridLayoutManager(this@GoodsListActivity, 2)
                        recyclerview!!.layoutManager = manager
                        adapter1 = GoodsListAdapter(this@GoodsListActivity, page_data)
                        //                        recyclerview.addItemDecoration(new DividerItemDecoration(GoodsListActivity.this, manager.getOrientation()));
                        recyclerview!!.addItemDecoration(SpaceItemDecoration(10))
                        recyclerview!!.adapter = adapter1


                        adapter1!!.setOnItemClickListener(object :GoodsListAdapter.OnItemClickListener{
                            override fun setOnItemClickListener(data: TypeListBean.ResultBean.PageDataBean?) {
                                val name:String = data!!.name
                                val cover_price = data.cover_price
                                val figure = data.figure
                                val product_id = data.product_id

                                val goodsBean = GoodsBean(name, cover_price, figure, product_id)
                                val intent = Intent(this@GoodsListActivity, GoodsInfoActivity::class.java)
                                intent.putExtra("goods_bean", goodsBean)
                                startActivity(intent)
                            }
                        })
                    }
                101 -> Toast.makeText(this@GoodsListActivity, "https", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun processData(response: String) {
        val gson = Gson()
        val typeListBean = gson.fromJson<TypeListBean>(response, TypeListBean::class.java!!)
        page_data = typeListBean.result!!.page_data
    }

}
