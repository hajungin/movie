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
    private int goodPoint;
    private String movieNo;
    private String userNo;


    public static BoardDto fromBoardEntity(Board board) {
        return new BoardDto(
                board.getBoardId(),
                board.getTitle(),
                board.getContent(),
                board.getGoodPoint(),
                board.getMovies().getMovieTitle(),
                board.getUser().getUserName()
        );
    }
}
