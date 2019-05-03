package com.example.aparcar20.adapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.aparcar20.data.ScreenContent;
import com.example.aparcar20.Splash;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private List <ScreenContent> screenContentList;

    public PagerAdapter(FragmentManager fm, List <ScreenContent> screenContentList) {
        super(fm);
        this.screenContentList = screenContentList;
    }

    @Override
    public Fragment getItem(int i) {

        Bundle params = new Bundle();
        params.putString("text", screenContentList.get( i ).getText() );
        params.putInt("image", screenContentList.get( i ).getImage() );
        params.putBoolean("showButton", screenContentList.get( i ).isShowButton() );

        Splash splash = new Splash();
        splash.setArguments( params );
        return splash ;




//        Splash splash = new Splash();
//        splash.setContent( screenContentList.get( i ) );
//        return splash ;
    }


    @Override
    public int getCount() {
        return screenContentList.size();
    }
}
