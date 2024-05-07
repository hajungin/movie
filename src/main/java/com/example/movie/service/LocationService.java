package com.example.movie.service;

import com.example.movie.dto.LocationDto;
import com.example.movie.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> findAll() {
        return locationRepository.findAll().stream()
                .map(x->LocationDto.fromLocationEntity(x))
                .toList();
    }
}
