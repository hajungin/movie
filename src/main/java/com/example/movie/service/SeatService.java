package com.example.movie.service;

import com.example.movie.repository.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
}
