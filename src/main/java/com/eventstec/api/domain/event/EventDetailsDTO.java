package com.eventstec.api.domain.event;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.eventstec.api.domain.coupon.Coupon;

public record EventDetailsDTO(UUID id, String title, String description, Date date, String city, String uf,
        Boolean remote, String eventUrl, String imgUrl, List<Coupon> coupons) {

}
