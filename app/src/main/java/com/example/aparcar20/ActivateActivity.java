package com.example.aparcar20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
