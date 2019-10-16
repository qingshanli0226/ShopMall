package com.example.homework1.Controller.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.homework1.Controller.Fragments.*
import com.example.homework1.R
import com.google.gson.JsonArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var manager: FragmentManager
    private lateinit var fragment_home : Fragment_home
    private lateinit var fragment_type: Fragment_type
    private lateinit var fragment_community: Fragment_community
    private lateinit var fragment_cart: Fragment_cart
    private lateinit var fragment_user: Fragment_user

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragment()
        initClick()
    }

    private fun initClick() {
        rg_foot.setOnCheckedChangeListener{group,checkedId->
            when(checkedId){
                R.id.rb_home ->  setHome()
                R.id.rb_type -> setType()
                R.id.rb_community ->  setCommunity()
                R.id.rb_cart ->  setCart()
                R.id.rb_user ->  setUser()
            }
        }
    }

    private fun setUser(){
        setTextBlack()
        rb_user.setTextColor(Color.argb(100,255,60,25))

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout_fragment,fragment_user)
        transaction.commit()
    }

    private fun setCart(){
        setTextBlack()
        rb_cart.setTextColor(Color.argb(100,255,60,25))

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout_fragment,fragment_cart)
        transaction.commit()
    }

    private fun setCommunity(){
        setTextBlack()
        rb_community.setTextColor(Color.argb(100,255,60,25))

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout_fragment,fragment_community)
        transaction.commit()
    }

    private fun setType(){
        setTextBlack()
        rb_type.setTextColor(Color.argb(100,255,60,25))

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout_fragment,fragment_type)
        transaction.commit()
    }

    private fun setHome(){
        setTextBlack()
        rb_home.setTextColor(Color.argb(100,255,60,25))

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout_fragment,fragment_home)
        transaction.commit()
    }

    private fun setTextBlack(){
        rb_home.setTextColor(Color.BLACK)
        rb_type.setTextColor(Color.BLACK)
        rb_community.setTextColor(Color.BLACK)
        rb_cart.setTextColor(Color.BLACK)
        rb_user.setTextColor(Color.BLACK)
    }

    private fun initFragment() {
        manager = supportFragmentManager

        fragment_home = Fragment_home()
        fragment_type = Fragment_type()
        fragment_community = Fragment_community()
        fragment_cart = Fragment_cart()
        fragment_user = Fragment_user()

        val transaction = manager.beginTransaction()
        transaction.add(R.id.layout_fragment,fragment_home)
        transaction.add(R.id.layout_fragment,fragment_type)
        transaction.add(R.id.layout_fragment,fragment_community)
        transaction.add(R.id.layout_fragment,fragment_cart)
        transaction.add(R.id.layout_fragment,fragment_user)
        transaction.commit()

        val transaction2 = manager.beginTransaction()
        transaction2.replace(R.id.layout_fragment,fragment_home)
        transaction2.commit()

    }
}
