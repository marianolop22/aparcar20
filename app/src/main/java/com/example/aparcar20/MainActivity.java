package com.example.aparcar20;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aparcar20.adapter.PagerAdapter;
import com.example.aparcar20.data.ScreenContent;

import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {

    TabLayout active;
    ViewPager welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome = findViewById(R.id.welcome);

        setTitle("Aparcar");
        List<ScreenContent> list =  new ArrayList<>();

        list.add( new ScreenContent( R.drawable.ic_register, "pantalla esta es una prueba de un texto grande", false));
        list.add( new ScreenContent( R.drawable.ic_car, "pantall sdfasdfasd", false));
        list.add( new ScreenContent( R.drawable.ic_payment, "pantalla blavlabla", true));

        welcome.setAdapter(new PagerAdapter(getSupportFragmentManager(), list ));

        active = findViewById(R.id.active);

        active.setupWithViewPager(welcome, true);


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
