package com.example.emtlab.service.application;

import com.example.emtlab.dto.CreateAccommodationDto;
import com.example.emtlab.dto.CreateCountryDto;
import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.DisplayCountryDto;
import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> reserve(Long id, String username);

    Optional<DisplayAccommodationDto> removeReservation(Long id);

    Optional<DisplayAccommodationDto> book(Long id, String username);

    Optional<DisplayAccommodationDto> completeStay(Long id);
}
