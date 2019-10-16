package com.example.myshopping.app.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myshopping.R
import com.example.myshopping.base.BaseFragment
import com.example.myshopping.community.CommunityFragment
import com.example.myshopping.home.fragment.HomeFragment
import com.example.myshopping.shoppingcart.ShoppingCartFragment
import com.example.myshopping.type.fragment.TypeFragment
import com.example.myshopping.user.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fragList = mutableListOf<BaseFragment>()
    var position: Int = 0;
    private var mContext: BaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initListener()
    }

    private fun initListener() {

        rg.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_home -> {
                    position = 0
                }
                R.id.rb_type -> {
                    position = 1
                }
                R.id.rb_faxian -> {
                    position = 2
                }
                R.id.rb_cart -> {
                    position = 3
                }
                R.id.rb_user -> {
                    position = 4
                }

            }

            val fragmeng = getFragmeng(position)
            switchFragment(mContext, fragmeng)
        }
        rg.check(R.id.rb_home)
    }

    private fun switchFragment(fromFragment: BaseFragment?, nextFragment: BaseFragment?) {
        if (this.mContext != nextFragment) {
            this.mContext = nextFragment
            if (nextFragment != null) {
                val transaction = supportFragmentManager.beginTransaction()
                if (!nextFragment.isAdded) {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit()
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment)
                    }
                    transaction.show(nextFragment).commit()
                }
            }
        }
    }


    private fun getFragmeng(position: Int): BaseFragment? {
        if (fragList != null && fragList.size > 0) {
            val fragment = fragList.get(position)
            return fragment
        }
        return null
    }

    private fun initFragment() {
        fragList.add(HomeFragment())
        fragList.add(TypeFragment())
        fragList.add(CommunityFragment())
        fragList.add(ShoppingCartFragment())
        fragList.add(UserFragment())
    }
}
