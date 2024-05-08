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
public class Movies {
    //영화 movie_no(pk), movie_title, movie_date(개봉일), movie_price, movie_rate, good_point_avg
    @Id
    @Column(name = "movie_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서 나중에 AUTO로 전환
    private Long movieNo;

    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Column(name = "movie_date")
    private LocalDateTime movieDate;

    @Column(name = "movie_rate")
    private String movieRate;//성인 등급 -> boolean 으로 yes or no로 처리 할 건지 아예 예전 Gender 처럼 enum 타입으로 만들건지 생각필요

    @Column(name = "good_point_avg")
    private double goodPointAvg; // board 엔티티 goodPoint 값 평균값으로 구할거니 double타입

}
