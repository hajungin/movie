package com.example.movie.dto;

import com.example.movie.entity.Location;
import com.example.movie.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDto {
    private Long seatId;
    private int seatRowNo;
    private int seatColumnNo;

    public static SeatDto fromSeatEntity(Seat seat){
        return new SeatDto(
                seat.getSeatId(),seat.getSeatRowNo(), seat.getSeatColumnNo()
        );
    }
}
