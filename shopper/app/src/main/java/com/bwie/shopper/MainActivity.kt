package com.bwie.shopper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bwie.shopper.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fragments:MutableList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        fragments= mutableListOf(HomeFragment(),HomeFragment(),HomeFragment(),HomeFragment(),HomeFragment())
        frameLayout.adapter=object :FragmentStatePagerAdapter(supportFragmentManager){
            override fun getItem(position: Int): Fragment {
                return fragments.get(position)
            }

            override fun getCount(): Int {
                return fragments.size
            }

        }
        rg_main.setOnCheckedChangeListener { radioGroup, i ->
            when(radioGroup.checkedRadioButtonId){
                R.id.rb_home->frameLayout.currentItem=0
                R.id.rb_type->frameLayout.currentItem=1
                R.id.rb_community->frameLayout.currentItem=2
                R.id.rb_cart->frameLayout.currentItem=3
                R.id.rb_user->frameLayout.currentItem=4
            }
        }
    }
}
