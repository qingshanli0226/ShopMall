package com.example.shoppingmall_kotlin.home.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.shoppingmall_kotlin.R
import com.example.shoppingmall_kotlin.home.adapter.FragmentHomeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentPagerAdapter = FragmentHomeAdapter(supportFragmentManager)
        viewPager.adapter = fragmentPagerAdapter

        initListener()
    }

    fun initListener() {
        /**
         * 切换页面
         */
        rg_main!!.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rb_home -> viewPager.currentItem = 0

                R.id.rb_type -> viewPager.currentItem = 1

                R.id.rb_community -> viewPager.currentItem = 2

                R.id.rb_cart -> viewPager.currentItem = 3

                R.id.rb_user ->viewPager.currentItem = 4

            }

        }
        rg_main!!.check(R.id.rb_home)
    }
}
