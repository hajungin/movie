package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "movie_no")
    private Long movieNo;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "good_point")
    private int goodPoint; // 별점 줄건데 소숫점까지 넣을거면 double로 수정필요
}

