package com.example.aparcar20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aparcar20.data.CardHolderIdentification;
import com.example.aparcar20.data.CreditCard;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    private Button startPayment;

    private Spinner documentType;
    private TextView idCreditCard;
    private TextView cvv;
    private TextView month;
    private TextView year;
    private TextView nameCreditCard;
    private TextView documentNumber;
    private TextView status;

    final ArrayList<String> docs = new ArrayList<>();
    private int positionSelected;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View payment = inflater.inflate(R.layout.fragment_payment, container, false);

        //Inicio variables
        startPayment = payment.findViewById(R.id.startPayment);
        documentType = payment.findViewById(R.id.documentType);
        idCreditCard = payment.findViewById(R.id.idCreditCard);
        cvv = payment.findViewById(R.id.cvv);
        month = payment.findViewById(R.id.month);
        year = payment.findViewById(R.id.year);
        nameCreditCard = payment.findViewById(R.id.nameCreditCard);
        documentNumber = payment.findViewById(R.id.documentNumber);
        status = payment.findViewById(R.id.status);

        docs.add("DNI");
        docs.add("CUIL");

        ArrayAdapter adapter = new ArrayAdapter  (payment.getContext()  ,
                android.R.layout.simple_spinner_item, docs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        documentType.setAdapter(adapter);

        documentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                positionSelected = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                positionSelected = -1;
            }
        });

        startPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initialize();
                startPaymentOnClick(v);
            }
        });



        return payment;
    }


    public void startPaymentOnClick ( final View v ) {
        MediaType MEDIA_TYPE = MediaType.parse("application/json");

        //okhttp
        OkHttpClient client = new OkHttpClient();

        CreditCard item = new CreditCard();

        item.setCard_number( idCreditCard.getText().toString()   ); //Nro de ""tarjeta. MANDATORIO
        item.setSecurity_code( cvv.getText().toString() ); // CVV. OPCIONAL
        item.setCard_expiration_month( month.getText().toString() ); //Mes de vencimiento [01-12]. MANDATORIO
        item.setCard_expiration_year( year.getText().toString());//Año de vencimiento[00-99]. MANDATORIO
        item.setCard_holder_name( nameCreditCard.getText().toString() ); //Nombre del titular tal como aparece en la tarjeta. MANDATORIO

        com.example.aparcar20.data.CardHolderIdentification idTitular = new CardHolderIdentification(); //Identificacion del titular de la tarjeta. Es opcional, pero debe estar completo si se agrega
        idTitular.setType( docs.get(positionSelected) );//MANDATORIO
        idTitular.setNumber( documentNumber.getText().toString() );//MANDATORIO
        item.setCard_holder_identification( idTitular );

        System.out.println( item.toJson() );

        RequestBody body = RequestBody.create(MEDIA_TYPE, item.toJson());

        Request request = new Request.Builder()
                .url("https://developers.decidir.com/api/v2/tokens")
                .post(body)
                .header("apikey", "e9cdb99fff374b5f91da4480c8dca741")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                status.setText( "Error " + e.toString() );
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    status.setText( "Unexpected code " + response );
                    throw new IOException("Unexpected code " + response);

                } else {

                    Gson gson = new Gson();
                    final JsonObject obj = gson.fromJson( response.body().string(), JsonObject.class );

//                    MeliActivity.this.runOnUiThread( new Runnable() {
//                        @Override
//                        public void run() {
//
//                            status.setText( "token " + obj.get("id").toString() );
//                        }});

                }
            }

        });
    }















}
