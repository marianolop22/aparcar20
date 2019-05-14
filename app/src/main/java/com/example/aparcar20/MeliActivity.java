package com.example.aparcar20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aparcar20.data.CreditCard;
import com.example.aparcar20.data.CardHolderIdentification;

//import com.android.decidir.sdk.DecidirPaymentToken;
//import com.android.decidir.sdk.dto.*;
//import com.android.decidir.sdk.exceptions.*;
//import com.android.decidir.sdk.services.DecidirCallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MeliActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meli);

        //Inicio variables
//        startPayment = findViewById(R.id.startPayment);
//        documentType = findViewById(R.id.documentType);
//        idCreditCard = findViewById(R.id.idCreditCard);
//        cvv = findViewById(R.id.cvv);
//        month = findViewById(R.id.month);
//        year = findViewById(R.id.year);
//        nameCreditCard = findViewById(R.id.nameCreditCard);
//        documentNumber = findViewById(R.id.documentNumber);
//        status = findViewById(R.id.status);
//
//        docs.add("DNI");
//        docs.add("CUIL");
//
//        ArrayAdapter adapter = new ArrayAdapter  (this,
//                android.R.layout.simple_spinner_item, docs);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        documentType.setAdapter(adapter);
//
//        documentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                positionSelected = position;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                positionSelected = -1;
//            }
//        });
//
//        startPayment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //initialize();
//                startPaymentOnClick(v);
//            }
//        });

    }


    @Override
    public void onClick(View v) {
    }

}
