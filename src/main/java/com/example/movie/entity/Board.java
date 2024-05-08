package com.example.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
<<<<<<< HEAD
import lombok.Builder;
=======
>>>>>>> 45abff84a3cb4e34f86ae4c60c8bb895959b6310
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
@Builder
public class Board {

    //  게시판 board_id(pk),title,content, movie_no,user_id, good_point
=======
public class Board {

//  게시판 board_id(pk),title,content, movie_no,user_id, good_point
>>>>>>> 45abff84a3cb4e34f86ae4c60c8bb895959b6310
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
<<<<<<< HEAD
=======

>>>>>>> 45abff84a3cb4e34f86ae4c60c8bb895959b6310
