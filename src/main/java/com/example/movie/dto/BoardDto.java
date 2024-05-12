package com.example.movie.dto;

import com.example.movie.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto {
    private Long boardId;
    private String title;
    private String content;
    private Long movieNo;
    private Long userId;

//    두 개 빼야됨
    private double goodPoint;
    private String movieTitle;

    public static BoardDto fromBoardEntity(Board board) {
        return new BoardDto(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getMovieNo(),
                board.getUserId(),
                board.getGoodPoint(),
                board.getMovieTitle()
        );
    }
}
