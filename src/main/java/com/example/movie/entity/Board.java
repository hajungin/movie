package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

//  게시판 board_id(pk),title,content, movie_no,user_id, good_point
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //사용해야할 DB가 db= postgreSQL라서  나중에 AUTO로 전환
    private Long boardId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

//    @ManyToOne
//    @JoinColumn(name = "movie_no")
//    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;



//    밑에 있는 거 다 빼야됨
    @Column(name = "movie_no")
    private Long movieNo;

    @Column(name = "good_point")
    private double goodPoint; // 별점 줄건데 소숫점까지 넣을거면 double로 수정필요

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "user_id")
    private Long userId;

}
