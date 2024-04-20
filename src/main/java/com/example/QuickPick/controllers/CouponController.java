package com.example.QuickPick.controllers;

import com.example.QuickPick.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor
public class CouponController {
    private  final CouponService couponService;

    @PostMapping
    public ResponseEntity<String> addCoupon(@RequestParam("couponCode")String couponCode,@RequestParam("discount")int discount){
        String response=couponService.addCoupon(couponCode,discount);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
