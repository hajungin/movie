package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ticket {

    //예매정보:ticket_no(pk),  movie_no(fk), user_no(fk), seat_id(fk), location_no(fk), reserve_date 상영날짜->저장할 곳이 필요
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서 나중에 AUTO로 전환
    @Column(name = "ticket_no")
    private Long ticketNo;

    @Column(name = "movie_no")
    private Long movieNo;

    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "location_no")
    private Long locationNo;

    @Column(name="seat_id")
    private Long seatId;

    @Column(name = "book_date")
    private LocalDateTime bookDate; //예약 날짜 (영화보는 날짜)

}
