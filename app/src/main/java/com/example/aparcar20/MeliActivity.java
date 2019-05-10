package com.example.aparcar20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aparcar20.data.ParkingItem;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MeliActivity extends AppCompatActivity implements View.OnClickListener {

    private MercadoPagoCheckout checkout;
    private Button startPayment;

    private TextView emailPayer;
    private TextView quantityPayer;
    private TextView status;

    private static final int REQUEST_CODE = 1;

    String preference_id = "434370851-61999814-2ea7-4133-b246-722034ff3a00";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meli);

        startPayment = findViewById(R.id.startPayment);
        emailPayer = findViewById(R.id.emailPayer);
        quantityPayer = findViewById(R.id.quantityPayer);
        status = findViewById(R.id.status);
        startPayment = findViewById(R.id.startPayment);

        startPayment.setOnClickListener(this);


//        checkout = new MercadoPagoCheckout.Builder("TEST-9f3aba21-5b8c-4660-acf8-1a85846c01c0", preference_id)
//                   .build();


//        startPayment.setOnClickListener( onClick()   );


    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        Log.i ("LOGCAT", "aca ando");


        if (requestCode == REQUEST_CODE) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                status.setText("Resultado del pago: " );
                //Done!
            } else if (resultCode == RESULT_CANCELED) {
                if (data != null && data.getExtras() != null
                        && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError =
                            (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);
                    status.setText("Error: " +  mercadoPagoError.getMessage());
                    //Resolve error in checkout
                } else {
                    status.setText("hola");
                    //Resolve canceled checkout
                }
            }
        }
    }


    public void startPaymentOnClick ( final View v ) {
        MediaType MEDIA_TYPE = MediaType.parse("application/json");

        //okhttp
        OkHttpClient client = new OkHttpClient();

        ParkingItem item = new ParkingItem( "parking",
                emailPayer.getText().toString(),
                Integer.parseInt( quantityPayer.getText().toString() ),
                14.50
        );

        RequestBody body = RequestBody.create(MEDIA_TYPE, item.toJson());

        Request request = new Request.Builder()
                .url("http://localhost:3000/TurnWM/meli/preferences")
                .post(body)
                //.header("Accept", "application/json")
                //.header("Content-Type", "application/json")
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

                    //        checkout = new MercadoPagoCheckout.Builder("TEST-9f3aba21-5b8c-4660-acf8-1a85846c01c0", "434370851-4ddf70e9-7c5a-47cf-84df-70b38c2abe0d")
                    //                .build();




                    Log.i ("LOGCAT", response.toString());
                }
            }

        });
    }

    @Override
    public void onClick(View v) {

        new MercadoPagoCheckout.Builder("TEST-9f3aba21-5b8c-4660-acf8-1a85846c01c0", preference_id).build()
                .startPayment(MeliActivity.this , REQUEST_CODE);

    }
}
