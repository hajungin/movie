package com.example.movie.api;

import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.service.BoardService;
import com.example.movie.service.LocationService;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("cinema")
@RestController
public class APiMainController {
    @Autowired
    BoardService boardService;
    @Autowired
    LocationService locationService;
    @Autowired
    MovieService movieService;

    @GetMapping("api")
    public List<Movies> mainView(){
        return movieService.RandomMovie();
    }

    @GetMapping("api/movie")
    public List<Movies> movieAll(){
        return movieService.findAllEm();
    }

//    @GetMapping("api/board/")
//    public List<Board> boards(){
//
//    }
}
