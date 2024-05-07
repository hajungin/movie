package com.example.movie.dto;


import com.example.movie.entity.Location;
import com.example.movie.entity.Movies;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviesDto {

    private Long movieNo;

    private String movieTitle;

    private LocalDateTime movieDate;

    private boolean movieRate;//성인 등급 -> boolean 으로 yes or no로 처리 할 건지 아예 예전 Gender 처럼 enum 타입으로 만들건지 생각필요

    private double goodPointAvg; // board 엔티티 goodPoint 값 평균값으로 구할거니 double타입

    public static MoviesDto fromMoviesEntity(Movies movies) {
        return new MoviesDto(
                movies.getMovieNo(), movies.getMovieTitle(), movies.getMovieDate(), movies.isMovieRate(),
                movies.getGoodPointAvg()
        );
    }

}
