package com.example.arpit.nurture;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadImageFragment extends Fragment {


    public UploadImageFragment() {
        // Required empty public constructor
    }

    Button browse;
    ImageView img;
    TextView notif;
    ArrayList<Bitmap> images = new ArrayList<>();
    Integer index;
    private static int RESULT_LOAD_IMG = 1;

    MyHelper helper;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    User currentUser = new User();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_upload_image, container, false);
        browse = v.findViewById(R.id.browse);
        img = v.findViewById(R.id.img);
        notif = v.findViewById(R.id.notif);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new MyHelper(getActivity());
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        edit = sp.edit();
        currentUser = helper.readUser(sp.getString("email", "null"));

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });
    }

    //Returning the image from the intent
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                img.setImageBitmap(selectedImage);
                images.add(selectedImage);
                index = images.size() - 1;
                currentUser.setUploadedImage(images);
                helper.addImages(sp.getString("email", "null"), images.get(index), index);
                Toast.makeText(getActivity(), "Image successfully uploaded", Toast.LENGTH_SHORT).show();
                notif.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }

}
