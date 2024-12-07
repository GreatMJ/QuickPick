package com.example.QuickPick.controllers;

import com.example.QuickPick.service.CouponService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupon")
@RequiredArgsConstructor @Validated
public class CouponController {
    private  final CouponService couponService;

    @PostMapping
    public ResponseEntity<String> addCoupon(@RequestParam("couponCode") @NotBlank(message = "Please provide coupon code") @Size(min = 5,max = 7,message = "Please provide valid coupon code.") String couponCode, @RequestParam("discount")@NotNull(message = "Please provide discount value")@Positive(message = "Discount should be greater than zero.") Integer discount){
        String response=couponService.addCoupon(couponCode,discount);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
