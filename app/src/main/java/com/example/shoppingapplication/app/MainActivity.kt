package com.example.shoppingapplication.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.shoppingapplication.R
import com.example.shoppingapplication.community.fragment.CommunityFragment
import com.example.shoppingapplication.home.fragment.HomeFragment
import com.example.shoppingapplication.shoppingcart.fragment.ShoppingCartFragment
import com.example.shoppingapplication.type.fragment.TypeFragment
import com.example.shoppingapplication.user.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var position = -1

    var fragments:MutableList<Fragment> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initListener()
    }

    fun initListener() {
        rg_main.setOnCheckedChangeListener { group, checkedId ->
            var fragment: Fragment? = when(checkedId){
                                            R.id.rb_home -> fragments.get(2)
                                            R.id.rb_type -> fragments.get(0)
                                            R.id.rb_community -> fragments.get(1)
                                            R.id.rb_cart -> fragments.get(3)
                                            R.id.rb_user -> fragments.get(4)
                                            else -> null
                                        }
            println("###   $fragment")
            var supportFragmentManager : FragmentManager? = supportFragmentManager
            var fragmentTransaction = supportFragmentManager?.beginTransaction()
            if (fragment != null) {
                if (fragmentTransaction != null) {
                   fragmentTransaction.replace(R.id.frame,fragment).commit()
                }
            }
        }
    }

    fun initFragment() {
        fragments.add(TypeFragment())
        fragments.add(CommunityFragment())
        fragments.add(HomeFragment())
        fragments.add(ShoppingCartFragment())
        fragments.add(UserFragment())
    }

}
