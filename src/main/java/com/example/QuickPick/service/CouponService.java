package com.example.QuickPick.service;

import com.example.QuickPick.models.Coupon;
import com.example.QuickPick.repository.CouponRepository;
import com.example.QuickPick.repository.CustomerRepository;
import com.example.QuickPick.transformers.CouponTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public String addCoupon(String couponCode,int discount){
        Coupon coupon= CouponTransformer.prepareCoupon(couponCode,discount);
        couponRepository.save(coupon);
        return "Coupon added successfully!";
    }
}
