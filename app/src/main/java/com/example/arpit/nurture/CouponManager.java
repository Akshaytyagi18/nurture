package com.example.arpit.nurture;

import android.app.Application;

import java.util.ArrayList;

public class CouponManager extends Coupon
{

    private ArrayList<Coupon> coup;

    public Integer getNoOfCoupons() {
        return noOfCoupons;
    }

    private Integer noOfCoupons; //No. of Coupons of same type

    public CouponManager() {
        coup = new ArrayList<Coupon>();
        noOfCoupons = 0;
    }

    public ArrayList<Coupon> getCoup() {
        return coup;
    }

    public void createNewCoupon(String code)

    {
        Coupon temp = new Coupon();
        temp.setCode(code);
        temp.setType(this.type);
        temp.setDescription(this.description);
        temp.setAmount(this.amount);
        temp.setCreditsRequired(this.creditsRequired);

        coup.add(temp);
        noOfCoupons ++;
    }

    public void redeemCupon()
    {
        coup.remove(0);
        noOfCoupons--;
    }
}
