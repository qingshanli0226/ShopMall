package com.example.homework

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homework.community.CommunityFragment
import com.example.homework.home.HomeFragment
import com.example.homework.shoppingcart.ShoppingCartFragment
import com.example.homework.type.TypeFragment
import com.example.homework.user.fragment.UserFragment
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var nowFragment = Fragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragmentList = mutableListOf<Fragment>()
        fragmentList.add(HomeFragment())
        fragmentList.add(TypeFragment())
        fragmentList.add(CommunityFragment())
        fragmentList.add(ShoppingCartFragment())
        fragmentList.add(UserFragment())

        switchFragment(fragmentList.get(0))

        rb_home.setOnClickListener { switchFragment(fragmentList.get(0)) }
        rb_type.setOnClickListener { switchFragment(fragmentList.get(1)) }
        rb_community.setOnClickListener { switchFragment(fragmentList.get(2)) }
        rb_cart.setOnClickListener { switchFragment(fragmentList.get(3)) }
        rb_user.setOnClickListener { switchFragment(fragmentList.get(4)) }

        //沉浸式状态栏
        StatusBarUtil.setColor(this, Color.GREEN)
    }

    fun switchFragment(currentFragment: Fragment){

        if (nowFragment != currentFragment) {
            val transaction = supportFragmentManager.beginTransaction()

            if (!currentFragment.isAdded) {
                if (nowFragment != null) {
                    transaction.hide(nowFragment)
                }

                transaction.add(R.id.frameLayout, currentFragment).commit()
            } else {
                transaction.hide(nowFragment).show(currentFragment).commit()
            }
            nowFragment = currentFragment
        }
    }
}
