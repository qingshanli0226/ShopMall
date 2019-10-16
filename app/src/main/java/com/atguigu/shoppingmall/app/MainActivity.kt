package com.atguigu.shoppingmall.app

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.RadioGroup

import com.atguigu.shoppingmall.R
import com.atguigu.shoppingmall.base.BaseFragment
import com.atguigu.shoppingmall.community.fragment.CommunityFragment
import com.atguigu.shoppingmall.home.fragment.HomeFragment
import com.atguigu.shoppingmall.shoppingcart.fragment.ShoppingCartFragment
import com.atguigu.shoppingmall.type.fragment.TypeFragment
import com.atguigu.shoppingmall.user.fragment.UserFragment

import java.util.ArrayList


import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    private lateinit var fragments: ArrayList<BaseFragment>
    private var position: Int = 0
    private var mContext: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initListener()
    }

    private fun initFragment() {
        fragments = ArrayList()
        fragments.add(HomeFragment(this))
        fragments.add(TypeFragment(this))
        fragments.add(CommunityFragment(this))
        fragments.add(ShoppingCartFragment(this))
        fragments.add(UserFragment(this))
    }

    private fun initListener() {
        rg_main.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_home -> position = 0
                R.id.rb_type -> position = 1
                R.id.rb_community -> position = 2
                R.id.rb_cart -> position = 3
                R.id.rb_user -> position = 4
            }//                        dismissAnmiation();
            //                        typeFragment.hideFragment();
            //                        dismissAnmiation();
            //初始化数据
            //                        int currentTab = typeFragment.getCurrentTab();
            //                        if (currentTab == 0) {
            //                            if (typeFragment.listFragment != null) {
            //                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //                                transaction.show(typeFragment.listFragment).commit();
            //                            }
            //                        } else {
            //                            if (typeFragment.tagFragment != null) {
            //                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            //                                transaction.show(typeFragment.tagFragment).commit();
            //                            }
            //                        }
            //                        typeFragment.hideFragment();
            //                        fragments.remove(fragments.get(3));
            //                        ShoppingCartFragment cartFragment = new ShoppingCartFragment();
            //                        fragments.add(3, cartFragment);
            //
            //                        typeFragment.hideFragment();
            //                        dismissAnmiation();
            //                        typeFragment.hideFragment();

            val baseFragment = getFragment(position)
            switchFragment(mContext, baseFragment)
        }

        rg_main!!.check(R.id.rb_home)

    }


    /**
     *
     * @param position
     * @return
     */
    private fun getFragment(position: Int): BaseFragment? {
        return if (fragments != null && fragments.size > 0) {
            fragments[position]
        } else null
    }

    private fun switchFragment(fromFragment: Fragment?, nextFragment: BaseFragment?) {
        if (mContext !== nextFragment) {
            mContext = nextFragment
            if (nextFragment != null) {
                val transaction = supportFragmentManager.beginTransaction()
                //判断nextFragment是否添加
                if (!nextFragment.isAdded) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit()
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.show(nextFragment).commit()
                }
            }
        }
    }

}
