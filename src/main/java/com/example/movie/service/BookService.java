package com.example.movie.service;

import com.example.movie.repository.SeatRepository;
import com.example.movie.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;

    public BookService(TicketRepository ticketRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
    }

    public List<Long> findSeatIdsByMovieNoAndLocationNoAndBookDate(Long movieNo, Long locationNo, LocalDate bookDate) {
        return ticketRepository.findSeatIdByMovieNoAndLocationNoAndBookDate(movieNo, locationNo, bookDate);
    }
}