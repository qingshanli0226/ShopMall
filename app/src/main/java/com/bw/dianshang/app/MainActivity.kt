package com.bw.dianshang.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.bw.dianshang.R
import com.bw.dianshang.community.fragment.CommunityFragment
import com.bw.dianshang.home.fragment.HomeFragment
import com.bw.dianshang.shoppingcart.fragment.CartFragment
import com.bw.dianshang.type.fragment.TypeFragment
import com.bw.dianshang.user.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        rg_main.setOnCheckedChangeListener { radioGroup, i ->
            var fragment:Fragment = Fragment()
            var homeFragment: HomeFragment =
                HomeFragment()
            var typeFragment: TypeFragment =
                TypeFragment()
            var communityFragment: CommunityFragment =
                CommunityFragment()
            var cartFragment: CartFragment =
                CartFragment()
            var userFragment: UserFragment =
                UserFragment()
            when(i){
                R.id.rb_home -> fragment = homeFragment
                R.id.rb_type -> fragment = typeFragment
                R.id.rb_community -> fragment = communityFragment
                R.id.rb_cart -> fragment = cartFragment
                R.id.rb_user -> fragment = userFragment
            }
            switchFragment(fragment)
        }

        rb_home.isChecked = true
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment).commit()
    }
}
