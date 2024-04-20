package com.example.QuickPick.transformers;

import com.example.QuickPick.models.Coupon;

public class CouponTransformer {
    public static Coupon prepareCoupon(String couponCode,int discount){
        return Coupon.builder()
                .couponCode(couponCode)
                .percentageDiscount(discount)
                .build();
    }
}
