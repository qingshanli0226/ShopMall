package com.bwie.shopper.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.atguigu.shoppingmall.app.MyAppliction
import com.atguigu.shoppingmall.home.adapter.HomeRecycleAdapter
import com.atguigu.shoppingmall.home.bean.ResultBean
import com.atguigu.shoppingmall.utils.Constants
import com.bwie.shopper.R
import com.bwie.shopper.utils.OkGoUtils
import com.google.gson.Gson
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment: Fragment(){
    var resultBean: ResultBean? = null
    lateinit var homeAdapter: HomeRecycleAdapter
    var handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.what==0){
                Log.d("lhf ",msg.obj.toString())
                val resultJson = msg.obj.toString()
                var resultBean = Gson().fromJson<ResultBean>(resultJson,ResultBean::class.java)
                activity!!.runOnUiThread (Runnable {
//                    homeAdapter.resultBean = resultBean
                    homeAdapter = HomeRecycleAdapter(MyAppliction.context,resultBean)
                    rv_home.layoutManager = LinearLayoutManager(context)
                    rv_home.adapter = homeAdapter
                    homeAdapter.notifyDataSetChanged()
                })
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_home,container,false)
        Log.i("TAG   hhh","sssss"  )
        onGetJson()
        println("TAG  ttttt"   )
        return view
    }

    fun onGetJson(){
        println("TAG  "   )
        OkGoUtils.getRequest<String>(Constants.HOME_URL,object : StringCallback(){

            override fun onSuccess(response: Response<String>?) {
                if (response != null) {
                    println("TAG      "+response.body().toString())
                    val obtain = Message.obtain()
                    obtain.what=1
                    obtain.obj=response.body().toString()
                    handler.sendMessage(obtain)

                }
            }
        })
    }
}
//class HomeFragment : BaseFragment() {
//    lateinit var resultBean: ResultBean
//    private var ib_top: ImageView? = null
//    private var adapter: HomeRecycleAdapter? = null
//    private var tv_search_home: TextView? = null
//    private var tv_message_home: TextView? = null
//
//    var currentType = BANNER
////    private val mLayoutInflater: LayoutInflater
//
//    private var isFirst = true
//    private var tvTime: TextView? = null
//    private var dt: Int = 0
//    private val handler = @SuppressLint("HandlerLeak")
//    object : Handler() {
//        override fun handleMessage(msg: Message) {
//            if (msg.what == 0) {
//                dt = dt - 1000
//                val sd = SimpleDateFormat("HH:mm:ss")
//                tvTime!!.text = sd.format(Date(dt.toLong()))
//
//                this.removeMessages(0)
//                this.sendEmptyMessageDelayed(0, 1000)
//                if (dt == 0) {
//                    this.removeMessages(0)
//                }
//            }else if (msg.what==1){
//                    println("TAGsss   "+msg.obj.toString())
//                    processData(msg.obj.toString())
//                adapter = HomeRecycleAdapter(MyAppliction.context, resultBean)
//                    val manager = GridLayoutManager(activity, 1)
//                //设置滑动到哪个位置了的监听
//                manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                    override fun getSpanSize(position: Int): Int {
//                        if (position <= 3) {
//                            ib_top!!.visibility = View.GONE
//                        } else {
//                            ib_top!!.visibility = View.VISIBLE
//                        }
//                        return 1
//                    }
//                }
//
//                    rv_home.layoutManager=manager
//                    initListener()
//                    rv_home.adapter = adapter
//
//                }
//            }
//    }
//
//    override fun initView(): View {
//        val view:View= View.inflate(context, R.layout.fragment_home, null)
//
////        initData()
//        initListener()
//        return view
//    }
//
//
//
//
//    override fun initData() {
//        super.initData()
//
//        println("TAG     inttdata")

//    }
//    private fun initListener() {
//        //置顶的监听
//        ib_top?.setOnClickListener { rv_home.scrollToPosition(0) }
//
//        //搜素的监听
//        tv_search_home?.setOnClickListener { Toast.makeText(MyAppliction.context, "搜索", Toast.LENGTH_SHORT).show() }
//
//        //消息的监听
//        tv_message_home?.setOnClickListener {
////            val intent = Intent(MyAppliction.context, MessageCenterActivity::class.java)
////            mainActivity.startActivity(intent)
//        }
//
//    }
//
//
//
//    private fun processData(json: String) {
//
//
//            //得到resultBean的数据
//            val ResultBean = Gson().fromJson(json,ResultBean::class.java)
//
////            val banner_info = ResultBean.getString("banner_info")
////            val act_info = ResultBean.getString("act_info")
////            val channel_info = ResultBean.getString("channel_info")
////            val hot_info = ResultBean.getString("hot_info")
////            val recommend_info = ResultBean.getString("recommend_info")
//            val seckill_info = ResultBean.getString("seckill_info")
//
//
//            resultBean = ResultBean()
//
//            //设置BannerInfoBean数据
//            val bannerInfoBeans = Gson().fromJson<ResultBean.BannerInfoBean>(ResultBean.getString("banner_info"), ResultBean.BannerInfoBean::class.java)
//            resultBean.banner_info = listOf(bannerInfoBeans)
////            val value = jsonObject.getString("value")
////            JSON.parseObject<com.atguigu.shoppingmall.home.bean.ResultBean.BannerInfoBean.ValueBean>(value, com.atguigu.shoppingmall.home.bean.ResultBean.BannerInfoBean.ValueBean::class.java)
//
//
//            //设置actInfoBeans数据
//            val actInfoBeans =  Gson().fromJson<ResultBean.ActInfoBean>(ResultBean.getString("act_info"), ResultBean.ActInfoBean::class.java)
//            resultBean.act_info = listOf(actInfoBeans)
//
//            //设置channelInfoBeans的数据
//            val channelInfoBeans = Gson().fromJson<ResultBean.ChannelInfoBean>(ResultBean.getString("channel_info"), ResultBean.ChannelInfoBean::class.java)
//            resultBean.channel_info = listOf(channelInfoBeans)
//
//            //设置hotInfoBeans的数据
//            val hotInfoBeans = Gson().fromJson<ResultBean.HotInfoBean>(ResultBean.getString("hot_info"), ResultBean.HotInfoBean::class.java)
//            resultBean.hot_info = listOf(hotInfoBeans)
//
//            //设置recommendInfoBeans的数据
//            val recommendInfoBeans = Gson().fromJson<ResultBean.RecommendInfoBean>(ResultBean.getString("recommend_info"), ResultBean.RecommendInfoBean::class.java)
//            resultBean.recommend_info = listOf(recommendInfoBeans)
//
//            //设置seckillInfoBean的数据
//            val seckillInfoBean = Gson().fromJson<ResultBean.SeckillInfoBean>(seckill_info, ResultBean.SeckillInfoBean::class.java)
//            resultBean.seckill_info = seckillInfoBean
//
//        }
//
//
//
//
//
//}