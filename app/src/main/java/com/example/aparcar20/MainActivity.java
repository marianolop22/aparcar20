package com.example.aparcar20;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    TabLayout active;
    ViewPager welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);

        List<ScreenContent> list =  new ArrayList<>();

        list.add( new ScreenContent( "url", "probando un texto", false));
        list.add( new ScreenContent( "url", "probando 2", false));
        list.add( new ScreenContent( "url", "probando 3", true));

        welcome.setAdapter(new PagerAdapter(getSupportFragmentManager(), list ));

        active = findViewById(R.id.active);
        active.setupWithViewPager(welcome, true);


//        active.setMax( list.size()-1 );

        //active.setProgress(0);

        welcome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.i("LOGACAT", "hola" + i);
                //active.setProgress(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //welcome.getCurrentItem();

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
