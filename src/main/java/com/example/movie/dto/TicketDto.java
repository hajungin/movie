package com.example.movie.dto;

import com.example.movie.entity.Seat;
import com.example.movie.entity.Ticket;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Long ticketNo;
    private Long movieNo;
    private Long userNo;
    private Long seatId;
    private Long locationNo;
    private LocalDate bookDate; //예약 날짜 (영화보는 날짜)




    public TicketDto(Long ticketNo, String movieTitle, String userName, String locationName, LocalDate bookDate) {
    }

    public static TicketDto fromTicketEntity(Ticket ticket){
        return new TicketDto(
                ticket.getTicketNo(),
                ticket.getMovies().getMovieTitle(),
                ticket.getUser().getUserName(),
                ticket.getLocation().getLocationName(),
                ticket.getBookDate()
        );
    }
}
