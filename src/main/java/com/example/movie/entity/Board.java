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
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ) //사용해야할 DB가 db= postgreSQL라서  나중에 AUTO로 전환
    private Long boardId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(name = "good_point",nullable = false)
    private int goodPoint;

    @ManyToOne
    @JoinColumn(name = "movie_no", nullable = false)
    private Movies movies;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    // 관람후기 페이지의 뿌릴 생성자 필요
    public Board(String title, String content, int goodPoint, Movies movies, User user) {
        this.title = title;
        this.content = content;
        this.goodPoint = goodPoint;
        this.movies = movies;
        this.user = user;
    }
}
