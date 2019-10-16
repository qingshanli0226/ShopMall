package com.atguigu.shoppingmall.user.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.app.LoginActivity
import com.atguigu.shoppingmall.app.MainActivity
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.user.activity.MessageCenterActivity
import com.atguigu.shoppingmall.utils.BitmapUtils
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.fragment_user.*

@SuppressLint("ValidFragment")
class UserFragment(var mainActivity: MainActivity) : BaseFragment(), View.OnClickListener {
    private var tvUserPay: TextView? = null
    private var tvUserReceive: TextView? = null
    private var tvUserFinish: TextView? = null
    private var tvUserDrawback: TextView? = null
    private var tvUserLocation: TextView? = null
    private var tvUserCollect: TextView? = null
    private var tvUserCoupon: TextView? = null
    private var tvUserScore: TextView? = null
    private var tvUserPrize: TextView? = null
    private var tvUserTicket: TextView? = null
    private var tvUserInvitation: TextView? = null
    private var tvUserCallcenter: TextView? = null
    private var tvUserFeedback: TextView? = null
    private var tvUsercenter: TextView? = null
    private var ibUserSetting: ImageButton? = null
    private var scrollView: ScrollView? = null

    /**
     * Find the Views in the layout<br></br>
     * <br></br>
     * Auto-created on 2016-10-08 09:07:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     *
     * @param view
     */
    private fun findViews(view: View) {
        tvUserPay = view.findViewById(R.id.tv_user_pay)
        tvUserReceive = view.findViewById(R.id.tv_user_receive)
        tvUserFinish = view.findViewById(R.id.tv_user_finish)
        tvUserDrawback = view.findViewById(R.id.tv_user_drawback)
        tvUserLocation = view.findViewById(R.id.tv_user_location)
        tvUserCollect = view.findViewById(R.id.tv_user_collect)
        tvUserCoupon = view.findViewById(R.id.tv_user_coupon)
        tvUserScore = view.findViewById(R.id.tv_user_score)
        tvUserPrize = view.findViewById(R.id.tv_user_prize)
        tvUserTicket = view.findViewById(R.id.tv_user_ticket)
        tvUserInvitation = view.findViewById(R.id.tv_user_invitation)
        tvUserCallcenter = view.findViewById(R.id.tv_user_callcenter)
        tvUserFeedback = view.findViewById(R.id.tv_user_feedback)
        tvUsercenter = view.findViewById(R.id.tv_usercenter)
        ibUserSetting = view.findViewById(R.id.ib_user_setting)
        scrollView = view.findViewById(R.id.scrollview)

        ib_user_icon_avator!!.setOnClickListener(this)
        ibUserSetting!!.setOnClickListener(this)
        tv_username.setOnClickListener(this)
    }

    /**
     * Handle button click events<br></br>
     * <br></br>
     * Auto-created on 2016-10-08 09:07:21 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    override fun onClick(v: View) {
        when(v){
            tv_username->{
                val intent = Intent(mainActivity, LoginActivity::class.java)
                //            startActivityForResult(intent, 0);
                startActivity(intent)
            }
            ibUserSetting->Toast.makeText(mainActivity, "设置", Toast.LENGTH_SHORT).show()
            ib_user_message->{
                val intent = Intent(mainActivity, MessageCenterActivity::class.java)
                startActivity(intent)
            }
        }
    }


    override fun initView(): View {
        val view = View.inflate(mainActivity, R.layout.fragment_user, null)
        findViews(view)
        tvUsercenter!!.alpha = 0f
        return view
    }

    @SuppressLint("Range", "ClickableViewAccessibility")
    override fun initData() {

        scrollView!!.setOnTouchListener { _, event ->
            val location = IntArray(2)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                }
                MotionEvent.ACTION_MOVE//下滑是正，上滑是负
                -> {
                    ib_user_icon_avator!!.getLocationOnScreen(location)//初始状态为125,即最大值是125，全部显示不透明是（40？）
                    val i = (location[1] - 40) / 85f
                    tvUsercenter!!.alpha = 1 - i
                }
            }
            false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            val screen_name = data!!.getStringExtra("screen_name")
            val profile_image_url = data.getStringExtra("profile_image_url")

            Picasso.with(mainActivity).load(profile_image_url).transform(object : Transformation {
                override fun transform(bitmap: Bitmap): Bitmap {
                    //先对图片进行压缩
                    //                Bitmap zoom = BitmapUtils.zoom(bitmap, DensityUtil.dip2px(mContext, 62), DensityUtil.dip2px(mContext, 62));
                    val zoom = BitmapUtils.zoom(bitmap, 90f, 90f)
                    //对请求回来的Bitmap进行圆形处理
                    val ciceBitMap = BitmapUtils.circleBitmap(zoom)
                    bitmap.recycle()//必须队更改之前的进行回收
                    return ciceBitMap
                }

                override fun key(): String {
                    return ""
                }
            }).into(ib_user_icon_avator)

            tv_username!!.text = screen_name
        }
    }
}
