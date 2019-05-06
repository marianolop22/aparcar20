package com.example.aparcar20;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

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

    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText rePassword;
    Button register;

    TextInputLayout emailRePasswordLayout;

    TextInputLayout passwordLayout;

    AlertDialog.Builder builder;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Variables de pantalla
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        rePassword = findViewById(R.id.rePassword);

        emailRePasswordLayout = findViewById(R.id.rePasswordLayout);
        passwordLayout = findViewById(R.id.passwordLayout);



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
                    emailRePasswordLayout.setError("las contraseñas deben ser iguales");
                } else {
                    emailRePasswordLayout.setError(null);
                }
            }
        });
    }

    public void btnRegisterOnClick ( final View v ) {

        Log.i("LOGCAT", rePassword.getText().toString() + " - " + password.getText().toString() );

        if ( !rePassword.getText().toString().equals( password.getText().toString() ))  {
            emailRePasswordLayout.setError("las contraseñas deben ser iguales");
        } else {

            emailRePasswordLayout.setError(null);

            Intent intentLogin = new Intent( v.getContext() , ActivateActivity.class );

            startActivity( intentLogin );
            //finish();


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
//
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    } else {
//
//                        System.out.println(response);
//
//                        Toast.makeText(v.getContext(), "Registrado de forma exitosa", Toast.LENGTH_SHORT ).show();
//
//                        Intent intentLogin = new Intent( v.getContext() , ActivateActivity.class );
//
//                        startActivity( intentLogin );
//
//
//                    }
//
//                }
//
//            });
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}


