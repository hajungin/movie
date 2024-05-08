package com.example.movie.dto;

import com.example.movie.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
    private Long boardId;
    private String title;
    private String content;
    private Long movieNo;
    private Long userId;
    private int goodPoint;

    public static BoardDto fromBoardEntity(Board board) {
        return new BoardDto(board.getBoardId(), board.getTitle(), board.getContent(),
                board.getMovieNo(), board.getUserId(), board.getGoodPoint());
    }

    public Board fromBoardDto(BoardDto dto) {
        Board board = new Board();
        board.setBoardId(dto.getBoardId());
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setMovieNo(dto.getMovieNo());
        board.setUserId(dto.getUserId());
        board.setGoodPoint(dto.getGoodPoint());
        return board;
    }
}
