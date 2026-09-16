package com.eventstec.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventstec.api.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
