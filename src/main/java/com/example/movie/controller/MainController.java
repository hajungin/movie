package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.LocationDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.repository.MoviesRepository;
import com.example.movie.service.BoardService;
import com.example.movie.service.LocationService;
import com.example.movie.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("cnema")
public class MainController {

    private final BoardService boardService;
    private final LocationService locationService;

    public MainController(BoardService boardService, LocationService locationService) {
        this.boardService = boardService;
        this.locationService = locationService;
    }


    @GetMapping("")
    public String mainView(){
        return "cnema/cnema_main";
    }

    @GetMapping("movie")
    public String movie(Model model){
//        List<MoviesDto> moviesDtoList = movieService.findAll();
        //        model.addAttribute("movie",moviesDtoList);
        List<Board> boardList = boardService.findAllem();
        model.addAttribute("board",boardList);
        return "show_movie";
    }

    @GetMapping("board")
    public String board(@RequestParam("boardId")Long boardId,
                        Model model){
        Board board = boardService.title(boardId);
        BoardDto boardDtoList = boardService.getOneBoard(boardId);
        log.info(boardDtoList.toString());
        model.addAttribute("board",board);
        return "show_board";
    }

    @GetMapping("location")
    public String location(Model model){
        List<LocationDto> locationDtoList = locationService.findAll();
        model.addAttribute("location",locationDtoList);
        return "show_location";
    }
}
