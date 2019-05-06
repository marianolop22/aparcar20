package com.example.aparcar20;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        final View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        text = rootView.findViewById(R.id.text);
        text.setText( content.getText() );

        image = rootView.findViewById(R.id.image);
        image.setImageResource( content.getImage()  );

        action = rootView.findViewById( R.id.begin);

        if ( content.isShowButton() ) {
            action.setVisibility ( View.VISIBLE );
        } else {
            action.setVisibility ( View.INVISIBLE );
        }

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentLogin = new Intent( rootView.getContext() , RegisterActivity.class );
                startActivity( intentLogin );

            }
        });


        return rootView;
    }

}
