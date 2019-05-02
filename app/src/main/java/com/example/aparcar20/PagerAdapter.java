package com.example.aparcar20;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.aparcar20.Splash;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private List <ScreenContent> screenContentList;

    public PagerAdapter(FragmentManager fm, List <ScreenContent> screenContentList) {
        super(fm);
        this.screenContentList = screenContentList;
    }

    @Override
    public Fragment getItem(int i) {

        Splash splash = new Splash();
        splash.setContent( screenContentList.get( i ) );
        return splash ;
    }


    @Override
    public int getCount() {
        return screenContentList.size();
    }
}
