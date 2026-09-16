package com.eventstec.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventstec.api.domain.address.Address;

public interface AddressRepository extends JpaRepository<Address, UUID> {

}
