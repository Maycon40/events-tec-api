package com.eventstec.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.coupon.CouponRequestDTO;
import com.eventstec.api.service.CouponService;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> create(@PathVariable UUID eventId, @RequestBody CouponRequestDTO body) {
        Coupon newCoupon = couponService.addCouponToEvent(eventId, body);

        return ResponseEntity.ok(newCoupon);
    }
}
