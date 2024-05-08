package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.repository.BoardRepository;
import com.example.movie.service.BoardService;
import com.example.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final MovieService movieService;

    public BoardController(BoardRepository boardRepository, BoardService boardService, MovieService movieService) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.movieService = movieService;
    }

    @GetMapping("board_view")
    public String viewAll(Model model) {
        List<BoardDto> boardDtoList = boardService.viewAllBoard();
        model.addAttribute("boardDto", boardDtoList);
        return "board/board_view";
    }

    @GetMapping("/board_insert")
    public String showBoardInsertForm(Model model) {
        List<MoviesDto> moviesDtoList = movieService.getAllMovies();
        model.addAttribute("moviesDtoList", moviesDtoList);
        model.addAttribute("boardDto", new BoardDto());
        return "board/board_insert";
    }

    @PostMapping("board_insert")
    public String boardInsert(@ModelAttribute("boardDto")BoardDto dto) {
        boardService.boardInsert(dto);
        return "redirect:/board/board_view";
    }
}
