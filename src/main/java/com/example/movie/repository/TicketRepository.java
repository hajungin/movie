package com.example.movie.repository;

import com.example.movie.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t FROM Ticket t WHERE t.movies.movieNo = :movieNo AND t.location.locationNo = :locationNo AND t.bookDate = :date")
    List<Ticket> searchMovieNoAndLocationNoAndDate(@Param("movieNo") Long movieNo,
                                                   @Param("locationNo") Long locationNo,
                                                   @Param("date") LocalDate date);
}
