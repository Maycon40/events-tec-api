package com.eventstec.api.domain.coupon;

public record CouponRequestDTO(String code, Long valid, Integer discount) {

}
