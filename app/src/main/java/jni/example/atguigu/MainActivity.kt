package jni.example.atguigu

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import jni.example.atguigu.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity<T> : AppCompatActivity() {
    var carFragment:CarFragment = CarFragment()
    var communityFragment: CommunityFragment = CommunityFragment()
    var homeFragment:HomeFragment<T> = HomeFragment()
    var personFragment:PersonFragment = PersonFragment()
    var typeFragment:TypeFragment = TypeFragment()

    var array:ArrayList<Fragment> = ArrayList()
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame, homeFragment).commit()
        rg_main.setOnCheckedChangeListener { group, chickId ->
            when (chickId) {
                R.id.rb_home -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    homeFragment
                ).commit()
                R.id.rb_type -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    typeFragment
                ).commit()
                R.id.rb_community -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    communityFragment
                ).commit()
                R.id.rb_cart -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    carFragment
                ).commit()
                R.id.rb_user -> supportFragmentManager.beginTransaction().replace(
                    R.id.frame,
                    personFragment
                ).commit()
            }
        }

        val handler : Handler = object : Handler(){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when(msg.what){

                }


            }
        }
        val service:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = Notification.Builder(this)
        //TODO 3:设置属性   setSamllIcon该属性必须设置
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置小图标
        builder.setContentTitle("我是标题");

        builder.setContentText("我是内容");
        //其他属性
        builder.setTicker("我是提示信息");
        builder.setContentInfo("我是附加信息");
        service.notify(1,builder.build())

        val bu = OkHttpClient
            .Builder()



    }
}