package com.example.arpit.nurture;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class Contactus extends Fragment {


    TextView img1,img2,img3,img4,img5 ;
    Button B;

    public Contactus() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contactus, container, false);
        img1 = (TextView) v.findViewById(R.id.imga1);
        img1.setText("NURTURE GROUPS PVT. LTD. \n E-mail : nurture.protectnature@gmail.com \n Facebook : enurtureindia1 \n Head Office : 3/16C , New Ashok Nagar, Delhi - 110047");

        img2 = (TextView) v.findViewById(R.id.imga2);
        img2.setText("ARPIT KUMAR WADHWA \n ( Programmer ) \n  Email: arpit.ak.kumar@gmail.com ");

        img3 = (TextView) v.findViewById(R.id.imga3);
        img3.setText("AVINASH PANDEY \n ( Programmer ) \n  Email: siblupandey@gmail.com ");

        img4 = (TextView) v.findViewById(R.id.imga4);
        img4.setText("ABHIMANYU VERMA \n ( Programmer ) \n  Email: abhimanyu.vo55@gmail.com ");

        img5 = (TextView) v.findViewById(R.id.imga5);
        img5.setText("PRANAV \n ( Programmer ) \n  Email: pranav.piyush@gmail.com ");

        B = (Button)v.findViewById(R.id.sendEmail);

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"nurture.protectnature@gmail.com"});
                startActivity(Intent.createChooser(intent,"Select how you want to send Email"));
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}