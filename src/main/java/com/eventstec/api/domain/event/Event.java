package com.eventstec.api.domain.event;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.eventstec.api.domain.address.Address;
import com.eventstec.api.domain.coupon.Coupon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    @JoinColumn(name = "img_url")
    private String imgUrl;

    @JoinColumn(name = "event_url")
    private String eventUrl;

    private Boolean remote;

    private Date date;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;
}
