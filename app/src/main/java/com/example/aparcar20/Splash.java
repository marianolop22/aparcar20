package com.example.aparcar20;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aparcar20.data.ScreenContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment {

    TextView text;
    ImageView image;
    Button action;

    ScreenContent content;

    public Splash( ) {
        // Required empty public constructor
        content = new ScreenContent();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content.setText( getArguments().getString("text") );
            content.setImage( getArguments().getInt("image") );
            content.setShowButton( getArguments().getBoolean("showButton") );
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        View rootView = inflater.inflate(R.layout.child_fragment_1_layout, container, false);
//        Button buttonInFragment1 = rootView.findViewById(R.id.button_1);
//        buttonInFragment1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "button in fragment 1", Toast.LENGTH_SHORT).show();
//            }
//        });

        final View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        text = rootView.findViewById(R.id.text);
        text.setText( content.getText() );

        image = rootView.findViewById(R.id.image);
        image.setImageResource( content.getImage()  );

        action = rootView.findViewById( R.id.action );

        if ( content.isShowButton() ) {
            action.setVisibility ( View.VISIBLE );
        } else {
            action.setVisibility ( View.INVISIBLE );
        }

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i ("BUTTON", "click en el boton");
                Intent intentLogin = new Intent( rootView.getContext() , RegisterActivity.class );


                //intentLogin.putExtra("email", email.getText().toString());
                //intentLogin.putExtra( "password", password.getText().toString());

                startActivity( intentLogin );

            }
        });


        return rootView;
    }

}
