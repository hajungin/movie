package com.example.movie.service;

import com.example.movie.dto.BoardDto;
import com.example.movie.entity.Board;
import com.example.movie.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> viewAllBoard() {
        List<BoardDto> boardDtoList = new ArrayList<>();
        return boardRepository.findAll()
                .stream()
                .map(x -> BoardDto.fromBoardEntity(x))
                .toList();
    }

    public void boardInsert(BoardDto dto) {
        Board board = dto.fromBoardDto(dto);
        boardRepository.save(board);
    }
}
