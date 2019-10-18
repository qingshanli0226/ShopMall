package com.example.kotlinshopping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlinshopping.fragment.home.FindFragment
import com.example.kotlinshopping.fragment.home.HomeFragment
import com.example.kotlinshopping.fragment.home.PersonalFragment
import com.example.kotlinshopping.fragment.home.ShopCarFragment
import com.example.kotlinshopping.fragment.type.SortFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
var fragments:MutableList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragments.add(HomeFragment())
        fragments.add(SortFragment())
        fragments.add(FindFragment())
        fragments.add(ShopCarFragment())
        fragments.add(PersonalFragment())
        MainPager.adapter = viewPagerAdapter(supportFragmentManager)
        rb_home.isChecked = true
        rg_main.setOnCheckedChangeListener{group, checkedId ->
            when(checkedId){
                R.id.rb_home -> MainPager.currentItem = 0
                R.id.rb_type -> MainPager.currentItem = 1
                R.id.rb_community -> MainPager.currentItem = 2
                R.id.rb_cart -> MainPager.currentItem = 3
                R.id.rb_user -> MainPager.currentItem = 4
            }
        }

    }


    inner class viewPagerAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }



    }

}
