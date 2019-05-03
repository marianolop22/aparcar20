package com.example.aparcar20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    TextView email;
    TextView password;
    Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Aparcar");

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnRegister ();
            }
        });





    }

    public void fnRegister () {
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        User user = new User( email.getText().toString(),  password.getText().toString()   );
//okhttp
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create( MEDIA_TYPE, user.toJson() );

        Request request = new Request.Builder()
                .url("http://www.aparcar.com.ar:8080/aparcar/v1/driver/register")
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    System.out.println( response );
                }
            }
        });

    }

}


