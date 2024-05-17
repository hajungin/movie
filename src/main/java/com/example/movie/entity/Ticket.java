package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Ticket {

    //예매정보:ticket_no(pk),  movie_no(fk), user_no(fk), seat_id(fk), location_no(fk), reserve_date 상영날짜->저장할 곳이 필요
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서 나중에 AUTO로 전환
    private Long ticketNo;

    @ManyToOne
    @JoinColumn(name = "movieNo")
    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private User user;

    @ManyToOne
    @JoinColumn(name = "locationNo")
    private Location location;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seatList = new ArrayList<>();

    @Column
    private LocalDate bookDate; //예약 날짜 (영화보는 날짜)

    private int totalPrice;


}
