package com.eventstec.api.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.coupon.CouponRequestDTO;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.repositories.CouponRepository;
import com.eventstec.api.repositories.EventRepository;

@Service
public class CouponService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CouponRepository couponRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO data) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not Found"));

        Coupon newCoupon = new Coupon();

        newCoupon.setCode(data.code());
        newCoupon.setDiscount(data.discount());
        newCoupon.setValid(new Date(data.valid()));
        newCoupon.setEvent(event);

        Coupon createdCoupon = couponRepository.save(newCoupon);

        return createdCoupon;
    }
}
