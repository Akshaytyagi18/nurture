package com.example.arpit.nurture;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;

public class User{

    public User( ) {

        uploadedImage = new ArrayList<>();
        credits = 0;
    }

    protected String name;
    protected String pass;
    protected String email;
    protected String mobNo;
    private Integer credits;
    private Bitmap profilePicture;
    public static Integer count; //Total no. of users using the app
    private ArrayList<Bitmap> uploadedImage;

    public ArrayList<Bitmap> getUploadedImage() {
        return uploadedImage;
    }

    public void setUploadedImage(ArrayList<Bitmap> uploadedImage) {
        this.uploadedImage = uploadedImage;
    }

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

    static {
        count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

}

