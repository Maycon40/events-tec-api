package com.eventstec.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventstec.api.domain.address.Address;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setUf(data.uf());
        address.setCity(data.city());
        address.setEvent(event);

        Address createdAddress = addressRepository.save(address);

        return createdAddress;
    }
}
