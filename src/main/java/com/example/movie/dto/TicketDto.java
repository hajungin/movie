package com.example.movie.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long ticketNo;
    private Long movieNo;
    private Long userNo;
    private Long seatId;
    private Long locationNo;
    private LocalDateTime bookDate; //예약 날짜 (영화보는 날짜)

    private MoviesDto moviesDto;
    private UserDto userDto;
    private SeatDto seatDto;

}
