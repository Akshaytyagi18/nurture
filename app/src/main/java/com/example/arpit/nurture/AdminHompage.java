package com.example.arpit.nurture;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

//Bitmap b1 = BitmapFactory.decodeResource(getResources(),R.drawable.linear2);
public class AdminHompage extends AppCompatActivity {


  String s[] = {};

    Bitmap b[] = {BitmapFactory.decodeResource(getResources(),R.drawable.natural_green_background),BitmapFactory.decodeResource(getResources(),R.drawable.background),BitmapFactory.decodeResource(getResources(),R.drawable.backofabout)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hompage);
        MyAdapterPhoto adapter = new MyAdapterPhoto(AdminHompage.this);
        ListView L = (ListView)findViewById(R.id.listofverificationphoto);
        L.setAdapter(adapter);
    }

    class MyAdapterPhoto extends ArrayAdapter<String>{



        public MyAdapterPhoto(@NonNull Context context) {
            super(context, R.layout.image_verification_plate,s);
        }

        @NonNull
        @Override
        public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = getLayoutInflater().inflate(R.layout.image_verification_plate,parent,false);
            ImageView img = (ImageView)v.findViewById(R.id.verificationphoto);
            Button yes = (Button)v.findViewById(R.id.realimage);
            Button no = (Button)v.findViewById(R.id.fakeimage);
            /*ArrayList<Integer> x = new ArrayList<>();
            x.add(R.drawable.natural_green_background);
            x.add(R.drawable.backofabout);
            img.setImageResource(x.get(position));*/

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            b[i].compress(Bitmap.CompressFormat.JPEG,50,bs);
            img.setImageBitmap(BitmapFactory.decodeByteArray(b[i],0,b.length));

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return v;
        }
    }



}
