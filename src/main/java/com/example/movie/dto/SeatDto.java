package com.example.movie.dto;

<<<<<<< HEAD
public class SeatDto {
=======
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
    private Long locationNo;
    private Long userNo;
    private int seatRowNo;
    private int seatColumnNo;
    private boolean seatSales;
>>>>>>> cloneV1
}
