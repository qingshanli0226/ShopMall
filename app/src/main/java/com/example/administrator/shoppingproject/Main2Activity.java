package com.example.administrator.shoppingproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.shoppingproject.Adpater.FramentAdpater;
import com.example.administrator.shoppingproject.Frament.FraHome;
import com.example.administrator.shoppingproject.Frament.Fra2;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ArrayList<Fragment> arr=new ArrayList<>();
    private ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vp = (ViewPager) findViewById(R.id.vp);


        FragmentPagerAdapter adp=new FramentAdpater(getSupportFragmentManager(),arr,this);
        FraHome fra=new FraHome();
        Fra2 fra2=new Fra2();
        arr.add(fra);
        arr.add(fra2);
        vp.setAdapter(adp);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (vp.getCurrentItem()==0){
                    Toast.makeText(Main2Activity.this, "1", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main2Activity.this, "2", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
