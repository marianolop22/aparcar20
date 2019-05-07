package com.example.aparcar20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivateActivity extends AppCompatActivity {

    Button activate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate);

        activate = findViewById(R.id.activate);

        activate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AppActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
