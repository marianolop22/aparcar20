package com.example.aparcar20;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Splash extends Fragment {

    TextView text;
    ImageView image;
    Button action;

    ScreenContent content;

    public Splash() {
        // Required empty public constructor
    }

    public void setContent ( ScreenContent content ) {
        this.content = content;
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

        View rootView = inflater.inflate(R.layout.fragment_splash, container, false);

        text = rootView.findViewById(R.id.text);
        text.setText( content.getText() );

        image = rootView.findViewById(R.id.image);
        image.setImageResource( R.drawable.ic_launcher_background  );

        action = rootView.findViewById( R.id.action );

        if ( content.isShowButton() ) {
            action.setVisibility ( View.VISIBLE );
        } else {
            action.setVisibility ( View.INVISIBLE );
        }

        return rootView;
    }

}
