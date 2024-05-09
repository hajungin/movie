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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서 나중에 AUTO로 전환
    private Long seatId;

    private int seatRowNo;
    private int seatColumnNo;

    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Ticket ticket;


}
