package com.example.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.homework.community.CommunityFragment
import com.example.homework.home.HomeFragment
import com.example.homework.shoppingcart.ShoppingCartFragment
import com.example.homework.type.TypeFragment
import com.example.homework.user.fragment.UserFragment
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

        switchFragment(fragmentList.get(0)).commit()

        rb_home.setOnClickListener { switchFragment(fragmentList.get(0)).commit() }
        rb_type.setOnClickListener { switchFragment(fragmentList.get(1)).commit() }
        rb_community.setOnClickListener { switchFragment(fragmentList.get(2)).commit() }
        rb_cart.setOnClickListener { switchFragment(fragmentList.get(3)).commit() }
        rb_user.setOnClickListener { switchFragment(fragmentList.get(4)).commit() }




    }

    fun switchFragment(currentFragment: Fragment):FragmentTransaction{
        val transaction = supportFragmentManager.beginTransaction()

        if (!currentFragment.isAdded){
            if (nowFragment != null){
                transaction.hide(nowFragment)
            }

            transaction.add(R.id.frameLayout, currentFragment)
        }else{
            transaction.hide(nowFragment).show(currentFragment)
        }

        nowFragment = currentFragment
        return transaction
    }
}
