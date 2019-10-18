package com.example.administrator.shoppingproject.App

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.WindowManager
import com.example.administrator.shoppingproject.Adpater.FramentAdpater
import com.example.administrator.shoppingproject.Frament.FraHome
import com.example.administrator.shoppingproject.Frament.Fra2
import com.example.administrator.shoppingproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var arr = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary)

        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            var viewG = window.decorView
            viewG.setBackgroundColor(Color.RED)
        }

        val fra = FraHome()
        val fra2 = Fra2()
        arr.add(fra)
        arr.add(fra2)
        val adp= FramentAdpater(supportFragmentManager, arr,this)

        vp_main.adapter=adp

        vp_main.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {


            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                if (vp_main.currentItem==0){
                    rb_home.isChecked=true
                }else if(vp_main.currentItem==1){
                    rb_type.isChecked =true
                }
            }

            override fun onPageSelected(p0: Int) {

            }

        })


        rb_home.setOnClickListener {
            vp_main.setCurrentItem(0)
        }
        rb_type.setOnClickListener {
            vp_main.setCurrentItem(1)
        }
    }
}
