package com.example.arpit.nurture;

public class Admin extends User{

    static private Integer noOfUploads; //Total no. of images uploaded by all users
    static private Integer noOfRequests; //Request for credit redemption

    static
    {
        noOfUploads = 0;
        noOfRequests = 0;
    }
}
