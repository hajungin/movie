package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class Seat {

    //좌석:seat_id(pk), location_no(fk), movie_no(fk), seat_row_no(a, b ,c), seat_column_no(1번석 2번석..)
    @Id
    @Column(name = "seat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서 나중에 AUTO로 전환
    private Long seatId;

    @Column(name = "location_no")
    private Long locationNo;

    @Column(name = "user_no")
    private Long userNo; //이것만 추가하면 굳이 ticket 테이블 필요 없을 듯?


    @Column(name = "seat_no")
    private int seatRowNo;

    @Column(name = "seat_column_no")
    private int seatColumnNo;

    @Column(name = "seat_sales")
    private boolean seatSales;

}
