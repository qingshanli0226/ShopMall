package com.atguigu.shoppingmall.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atguigu.shoppingmall.app.MainActivity

/**
 * 作者：尚硅谷-杨光福 on 2016/11/13 15:16
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：BaseFragment
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    /**
     * 有子类实现，实现特有效果
     * @return
     */
    abstract fun initView(): View

    /**
     * 初始化数据
     */
    open fun initData() {

    }


}
