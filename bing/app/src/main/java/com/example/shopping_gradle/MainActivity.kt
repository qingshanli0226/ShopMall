



package com.example.shopping_gradle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.RadioGroup
import com.example.shopping_gradle.Controller.Adapter.Main_FragAdapter
import com.example.shopping_gradle.Controller.Fragment.ClassIFications_Fragment
import com.example.shopping_gradle.Controller.Fragment.Home_Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mlist= mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Home:Home_Fragment = Home_Fragment()
        var ClassI:ClassIFications_Fragment= ClassIFications_Fragment()
        mlist.add(Home)
        mlist.add(ClassI)


        var fragAdapter:Main_FragAdapter = Main_FragAdapter(supportFragmentManager,mlist)
        Main_frameLayout.adapter=fragAdapter


        Main_rg_main.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{

            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.Main_rb_home->Main_frameLayout.currentItem=0
                    R.id.Main_rb_type->Main_frameLayout.currentItem=1
                }
            }

        })


    }
}
