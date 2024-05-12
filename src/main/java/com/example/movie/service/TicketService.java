package com.example.movie.service;

import com.example.movie.dto.TicketDto;
import com.example.movie.entity.Ticket;
import com.example.movie.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDto> findAll() {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        return ticketRepository.findAll()
                .stream()
                .map(x->TicketDto.fromTicketEntity(x))
                .toList();
    }
}
