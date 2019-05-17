package com.example.aparcar20;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aparcar20.barcode.ScanActivity;
import com.example.aparcar20.data.CardHolderIdentification;
import com.example.aparcar20.data.CreditCard;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
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

//    private BarcodeDetector barcodeDetector;
//    private CameraSource cameraSource;
//    private SurfaceView cameraView;
//    private Button close;
    private Button scan;

    private TextView mResultTextView;
    private int BARCODE_READER_REQUEST_CODE = 1;


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


//        cameraView = payment.findViewById(R.id.camera_view);
//        close = payment.findViewById(R.id.closeButton);

        mResultTextView = payment.findViewById(R.id.result_textview);
        scan = payment.findViewById(R.id.scanButton);

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ScanActivity.class);
                startActivityForResult(intent, BARCODE_READER_REQUEST_CODE);


//                Log.i("LOGCAT", "ABRO");
//                cameraView.setVisibility( View.VISIBLE );
//                close.setVisibility(View.VISIBLE);
//                scan.setVisibility( View.GONE );
//                startScaning();
            }
        });

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(ScanActivity.BarcodeObject);
                            //Point p = new Point( barcode.cornerPoints );
                    mResultTextView.setText( barcode.displayValue );
                } else
                    mResultTextView.setText("nada capturado");
            } else
                Log.e("LOGCAT", "Error de formato" );
        } else
            super.onActivityResult(requestCode, resultCode, data);

    }

    public void startPaymentOnClick (final View v ) {
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
                //status.setText( "Error " + e.toString() );
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //status.setText( "Unexpected code " + response );
                    throw new IOException("Unexpected code " + response);

                } else {

                    Gson gson = new Gson();
                    final JsonObject obj = gson.fromJson( response.body().string(), JsonObject.class );

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            status.setText( "token " + obj.get("id").toString() );
                        }
                    });
                }
            }

        });
    }




//    public void startScaning () {
//
//
//        Log.i("LOGCAT", "acá entro en la funcion");
//        // creo el detector qr
//        barcodeDetector =
//                new BarcodeDetector.Builder(getContext())
//                        .setBarcodeFormats(Barcode.QR_CODE)
//                        .build();
//
//        // creo la camara fuente
//        cameraSource = new CameraSource
//                .Builder(getContext(), barcodeDetector)
//                .setRequestedPreviewSize(640, 480)
//                .build();
//
//
//        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//
//                // verifico si el usuario dio los permisos para la camara
//                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    try {
//                        cameraSource.start(cameraView.getHolder());
//                    } catch (IOException ie) {
//                        Log.i("LOGCAT", ie.getMessage());
//                    }
//                } else {
//                    Toast.makeText(getContext(), "error en la camara", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//                close.setVisibility(View.INVISIBLE);
//                cameraView.setVisibility( View.INVISIBLE );
//
//                scan.setVisibility(View.VISIBLE);
//
//            }
//
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//
//                Log.i("LOGCAT", "ando por acá " + barcodes.size());
//
//                if (barcodes.size() != 0) {
//                    Log.i ( "LOGCAT", "codigo " + barcodes.valueAt(0).displayValue.toString() );
//                    // hacer algo
//
//
//
//                    barcodeDetector.release();
//
//
//                }
//
//
//            }
//        });
//    }










}
