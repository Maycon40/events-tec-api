package com.eventstec.api.domain.event;

import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(String title, String description, Long date, String city, String uf, Boolean remote, String eventUrl, MultipartFile image) {

}
