package com.example.movie.service;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
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

    public void insert(BoardDto dto) {
        Board board = Board.builder()
                .boardId(dto.getBoardId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .movieNo(dto.getMovieNo())
                .userId(dto.getUserId())
                .goodPoint(dto.getGoodPoint())
                .movieTitle(dto.getMovieTitle())
                .build();
        boardRepository.save(board);
    }

    public BoardDto getOneBoard(Long id) {
        return boardRepository.findById(id)
                .map(x -> BoardDto.fromBoardEntity(x))
                .orElse(null);
    }

    public void update(BoardDto boardDto) {
        Board board = Board.builder()
                .boardId(boardDto.getBoardId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .userId(boardDto.getUserId())
                .goodPoint(boardDto.getGoodPoint())
                .movieTitle(boardDto.getMovieTitle())
                .build();
        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
