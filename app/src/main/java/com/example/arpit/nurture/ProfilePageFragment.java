package com.example.arpit.nurture;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_PHONE;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment {


    public ProfilePageFragment() {
        // Required empty public constructor
    }

    ImageView profilePicture, plusButton;
    TextView name,email,mobNo;
    View layout;
    ViewGroup cont;
    Bitmap dp;

    MyHelper helper;
    SharedPreferences sp;
    User currentUser = new User();
    SharedPreferences.Editor edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_profile_page, container, false);
        profilePicture = v.findViewById(R.id.profile_picture);
        plusButton = v.findViewById(R.id.plus_button);
        name = v.findViewById(R.id.new_user);
        email = v.findViewById(R.id.email_id);
        mobNo = v.findViewById(R.id.mob_no);
        cont = container;

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        helper = new MyHelper(getActivity());
        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        edit = sp.edit();
        currentUser = helper.readUser(sp.getString("email", "null"));

        if(currentUser != null){

            if(currentUser.getProfilePicture() != null){
                profilePicture.setImageBitmap(currentUser.getProfilePicture());
            }

            if(currentUser.getName() == null){
                name.setText("Click to add Name");
            }
            else{
                name.setText(currentUser.getName());
            }

            if(currentUser.getMobNo() == null){
                mobNo.setText("Click to add Number");
            }
            else {
                mobNo.setText(currentUser.getMobNo());
            }

            email.setText(currentUser.getEmail());
        }



        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.custom_dialog_box, cont, false);

        final TextView msg = layout.findViewById(R.id.message);
        final EditText val = layout.findViewById(R.id.value);
        final TextView tit = layout.findViewById(R.id.title);


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        final AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());
        ab.setView(layout);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tit.setText("Change Name");
                msg.setText("Enter New name");
                val.setHint("Name");

                if(layout.getParent()!=null)
                {
                    ((ViewGroup)layout.getParent()).removeView(layout); // <- fix
                    tit.setText("Change Name");
                    msg.setText("Enter New name");
                    val.setHint("Name");
                    val.setText("");
                    val.setInputType(TYPE_CLASS_TEXT);
                    ab.setView(layout);
                }


                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = val.getText().toString();

                        if(newName.length() >0) {
                            name.setText(newName);
                            currentUser.setName(newName);
                            helper.updateUser(currentUser, sp.getString("email", "null"));

                        }
                    }
                });

                ab.setNeutralButton("CANCEL", null);
                ab.setCancelable(false);

                ab.show();
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tit.setText("Change Email");
                msg.setText("Enter New email");
                val.setHint("Email");

                if(layout.getParent()!=null)
                {
                    ((ViewGroup)layout.getParent()).removeView(layout); // <- fix
                    tit.setText("Change Email");
                    msg.setText("Enter New email");
                    val.setHint("Email");
                    val.setText("");
                    val.setInputType(TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    ab.setView(layout);
                }
                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmail = val.getText().toString();

                        if(newEmail.length() > 0) {
                            email.setText(newEmail);
                            currentUser.setEmail(newEmail);
                            helper.updateUser(currentUser, sp.getString("email", "null"));
                            edit.putString("email", currentUser.getEmail());
                            edit.commit();
                        }
                    }
                });

                ab.setNeutralButton("CANCEL", null);
                ab.setCancelable(false);

                ab.show();
            }
        });

        mobNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tit.setText("Change Mobile No.");
                msg.setText("Enter New Mobile Number");
                val.setHint("Mobile Number");
                val.setInputType(TYPE_CLASS_NUMBER);

                if(layout.getParent()!=null)
                {
                    ((ViewGroup)layout.getParent()).removeView(layout); // <- fix
                    tit.setText("Change Mobile No.");
                    msg.setText("Enter New Mobile Number");
                    val.setHint("Mobile Number");
                    val.setText("");
                    val.setInputType(TYPE_CLASS_NUMBER);
                    ab.setView(layout);
                }

                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newNo = val.getText().toString();

                        if(newNo.length() >0){
                            mobNo.setText(newNo);
                            currentUser.setMobNo(newNo);
                            helper.updateUser(currentUser, sp.getString("email", "null"));
                        }
                    }
                });

                ab.setNeutralButton("CANCEL", null);
                ab.setCancelable(false);

                ab.show();
            }
        });

    }

    //Returning the image from the intent


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                dp = selectedImage;
                profilePicture.setImageBitmap(dp);
                currentUser.setProfilePicture(dp);
                helper.updateUser(currentUser, sp.getString("email", "null"));
                Toast.makeText(getActivity(), "Image successfully uploaded", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getActivity(), "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }

}
