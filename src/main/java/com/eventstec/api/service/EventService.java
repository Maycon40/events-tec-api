package com.eventstec.api.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.domain.event.EventResponseDTO;
import com.eventstec.api.domain.event.EventDetailsDTO;
import com.eventstec.api.repositories.CouponRepository;
import com.eventstec.api.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = "";

        if (data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();

        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setImgUrl(imgUrl);
        newEvent.setDate(new Date(data.date()));
        newEvent.setRemote(data.remote());

        Event createdEvent = eventRepository.save(newEvent);

        if (!createdEvent.getRemote()) {
            addressService.createAddress(data, newEvent);
        }

        return createdEvent;

    }

    private String uploadImg(MultipartFile file) {
        return "";
    }

    public EventDetailsDTO getEventById(UUID eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        List<Coupon> coupons = couponRepository.findByEventIdAndValidAfter(eventId, new Date());

        return new EventDetailsDTO(event.getId(), event.getTitle(), event.getDescription(), event.getDate(),
                event.getAddress().getCity(), event.getAddress().getUf(), event.getRemote(), event.getEventUrl(),
                event.getImgUrl(), coupons);
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Event> eventsPage = eventRepository.findUpcomingEvents(new Date(), pageable);

        return eventsPage.map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl())).stream().toList();
    }

    public List<EventResponseDTO> getFilteredEvents(int page, int size, String title, String city, String uf,
            Date startDate, Date endDate) {
        title = (title != null) ? title : "";
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date();

        Pageable pageable = PageRequest.of(page, size);

        Page<Event> eventsPage = eventRepository.findFilteredEvents(title, city, uf, startDate, endDate,
                pageable);

        return eventsPage.map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ? event.getAddress().getCity() : "",
                event.getAddress() != null ? event.getAddress().getUf() : "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl())).stream().toList();
    }
}
