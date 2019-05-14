package com.example.aparcar20;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aparcar20.custom.CustomEditText;
import com.example.aparcar20.data.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    CustomEditText email;
    CustomEditText password;
    CustomEditText rePassword;
    Button register;

    AlertDialog.Builder builder;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Variables de pantalla
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.rePassword);


        register = findViewById(R.id.register);



        //Alerta
        builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialoglayout);
        builder.setCancelable(false);
        dialog = builder.create();

        //Evento de botones
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegisterOnClick ( v );
            }
        });

        rePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if ( (!rePassword.getText().toString().equals("") ) && (!rePassword.getText().equals( password.getText() )) ) {

                } else {

                }
            }
        });
    }

    public void btnRegisterOnClick ( final View v ) {

        Intent intent = new Intent(v.getContext(), AppActivity.class);
        startActivity(intent);




//        if ( !rePassword.getText().toString().equals( password.getText().toString() ))  {
//
//        } else {
//
//
//
//            MediaType MEDIA_TYPE = MediaType.parse("application/json");
//            User user = new User(email.getText().toString(), password.getText().toString());
//
//            //okhttp
//            OkHttpClient client = new OkHttpClient();
//            RequestBody body = RequestBody.create(MEDIA_TYPE, user.toJson());
//
//            Request request = new Request.Builder()
//                    .url("http://www.aparcar.com.ar:8080/aparcar/v1/driver/register")
//                    .post(body)
//                    .header("Accept", "application/json")
//                    .header("Content-Type", "application/json")
//                    .build();
//
//            dialog.show();
//
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                    dialog.dismiss();
//                }
//
//                @Override
//                public void onResponse(Call call, final Response response) throws IOException {
//                    dialog.dismiss();
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    } else {
//                        Intent intent = new Intent(v.getContext(), ActivateActivity.class);
//                        startActivity(intent);
//                    }
//                }
//
//            });
//
//        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}


