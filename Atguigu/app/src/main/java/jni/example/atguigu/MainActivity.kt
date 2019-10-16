package jni.example.atguigu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import jni.example.atguigu.fragment.*
import jni.example.atguigu.home.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var carFragment:CarFragment = CarFragment()
    var communityFragment: CommunityFragment = CommunityFragment()
    var homeFragment: HomeFragment =
        HomeFragment()
    var personFragment:PersonFragment = PersonFragment()
    var typeFragment:TypeFragment = TypeFragment()

    var array:ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame,homeFragment).commit()

        rg_main.setOnCheckedChangeListener { group, chickId ->
            when(chickId){
                R.id.rb_home ->supportFragmentManager.beginTransaction().replace(R.id.frame,homeFragment).commit()
                R.id.rb_type ->supportFragmentManager.beginTransaction().replace(R.id.frame,typeFragment).commit()
                R.id.rb_community ->supportFragmentManager.beginTransaction().replace(R.id.frame,communityFragment).commit()
                R.id.rb_cart ->supportFragmentManager.beginTransaction().replace(R.id.frame,carFragment).commit()
                R.id.rb_user ->supportFragmentManager.beginTransaction().replace(R.id.frame,personFragment).commit()
            }
        }




    }
}
