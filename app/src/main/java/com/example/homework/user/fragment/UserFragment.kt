package com.example.homework.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.homework.R
import com.example.homework.app.LoginActivity
import com.example.homework.user.activity.MessageCenterActivity
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_user, null)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置
        ib_user_setting.setOnClickListener { toast("设置") }
        //跳转信息界面
        ib_user_message.setOnClickListener{ startActivity<MessageCenterActivity>()}

        //跳转登录界面
        ib_user_icon_avator.setOnClickListener { startActivity<LoginActivity>() }

        tv_all_order.setOnClickListener { toast("查看全部订单") }

        //待付款
        tv_user_pay.setOnClickListener { toast("待付款") }

        //待发货
        tv_user_sendOut.setOnClickListener { toast("待发货") }

        //待收货
        tv_user_receive.setOnClickListener { toast("待收货") }

        //已完成
        tv_user_finish.setOnClickListener { toast("已完成") }

        //售后退货
        tv_user_drawback.setOnClickListener { toast("售后/退款") }
    }

}