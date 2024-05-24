package com.example.movie.api;

import com.example.movie.service.BoardService;
import com.example.movie.service.BookService;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class APiAdminController {
    @Autowired
    UserService userService;
    MovieService movieService;
    BoardService boardService;
    BookService bookService;

//    @GetMapping("")

}
