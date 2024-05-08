package com.example.movie.repository;

import com.example.movie.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Long> findSeatIdByMovieNoAndLocationNoAndBookDate(Long movieNo, Long locationNo, LocalDate bookDate);
}
