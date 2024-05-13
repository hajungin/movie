package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.repository.MoviesRepository;
import com.example.movie.service.BoardService;
import com.example.movie.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("cnema")
public class MainController {

    private final MovieService movieService;
    private final BoardService boardService;

    public MainController(MovieService movieService, BoardService boardService) {
        this.movieService = movieService;
        this.boardService = boardService;
    }


    @GetMapping("")
    public String mainView(){
        return "cnema/cnema_main";
    }

    @GetMapping("movie")
    public String movie(Model model){
        List<MoviesDto> moviesDtoList = movieService.findAll();

        model.addAttribute("movie",moviesDtoList);

        return "show_movie";
    }

    @GetMapping("board")
    public String board(Model model){
        List<BoardDto> boardDtoList = boardService.findAll();
        model.addAttribute("board",boardDtoList);
        return "show_board";
    }
}
