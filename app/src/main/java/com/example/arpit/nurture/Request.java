package com.example.arpit.nurture;

public class Request {

    private String userEmail;
    private Coupon couponSelected;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Coupon getCouponSelected() {
        return couponSelected;
    }

    public void setCouponSelected(Coupon couponSelected) {
        this.couponSelected = couponSelected;
    }
}
