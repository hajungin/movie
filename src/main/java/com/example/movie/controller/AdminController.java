package com.example.movie.controller;

import com.example.movie.dto.BoardDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.TicketDto;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.Board;
import com.example.movie.entity.Movies;
import com.example.movie.entity.User;
import com.example.movie.service.BoardService;
import com.example.movie.service.MovieService;
import com.example.movie.service.TicketService;
import com.example.movie.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
    @Autowired
    EntityManager em;
    private String uploadDir;
    private final UserService userService;
    private final MovieService movieService;
    private final TicketService ticketService;
    private final BoardService boardService;

    public AdminController(UserService userService, MovieService movieService, TicketService ticketService, BoardService boardService) {
        this.userService = userService;
        this.movieService = movieService;
        this.ticketService = ticketService;
        this.boardService = boardService;
    }

    @GetMapping("")
//    관리자 페이지 메인 화면
    public String adminView(){
        return "admin/main";
    }

    @GetMapping("user")
//    관리자페이지 회원관리 화면
    public String userView(Model model){
        //        레포지토리 사용
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user",userDtoList);

//        엔티티 매니저 사용
//        List<User> userList = userService.findAllEm();
//        model.addAttribute("user",userList);
        return "admin/user";
    }

    @GetMapping("update")
//    관리자페이지 회원 수정화면
    public String updateView(@RequestParam("updateId")Long userNo,
                             Model model){
        UserDto userDto = userService.getOneUser(userNo);
        model.addAttribute("userDto",userDto);

//        User user = userService.getOneUserEm(userNo);
//        model.addAttribute("userDto",user);
        return "admin/user_update";
    }
    @PostMapping("update")
    public String updateView(@ModelAttribute("userDto") UserDto userDto){
        userService.update(userDto);
        return "redirect:/admin/user";
    }

    @PostMapping("/delete/{deleteId}")
//    관리자페이지 회원삭제
    public String delete(@PathVariable("deleteId")Long id){
//        userService.delete(id);
//        테이블이 foreign key 로 연결되어 있어 엔티티 매니저를 사용하여 삭제
        userService.delete(id);
        return "redirect:/admin/user";
    }

    @GetMapping("movie")
    public String movieView(Model model) {
//        List<MoviesDto> moviesDtoList = movieService.findAll();
//        model.addAttribute("movie", moviesDtoList);
        List<Movies> moviesList = movieService.findAllEm();
        model.addAttribute("movie", moviesList);
        return "admin/movie";
    }


    @GetMapping("movie_update")
//    관리자페이지 영화수정 화면
    public String movieUpdateView(@RequestParam("updateId")Long movieNo,
                             Model model){

        MoviesDto moviesDto = movieService.getOneMovie(movieNo);
        model.addAttribute("movie",moviesDto);
        return "admin/movie_update";
    }
    @PostMapping("movie_update")
    public String movieUpdateView(@ModelAttribute("moviesDto") MoviesDto moviesDto){
        movieService.update(moviesDto);
        return "redirect:/admin/movie";
    }

    @PostMapping("/deleted/{deleteId}")
//    관리자페이지 영화삭제 화면
    public String deleteMovie(@PathVariable("deleteId")Long movieNo){
        movieService.delete(movieNo);
        return "redirect:/admin/movie";
    }

    @GetMapping("insert")
//    관리자페이지 영화 등록 화면
    public String insertMovie(Model model){
        model.addAttribute("movie", new MoviesDto());
        return "admin/movie_insert";
    }
    @PostMapping("insert")
    public String insertMovie(@ModelAttribute("movie")MoviesDto dto){
        movieService.insert(dto);
        return "redirect:/admin/movie";
    }

    @GetMapping("ticket")
    public String ticket(Model model){
        List<TicketDto> ticketDtoList = ticketService.findAll();
        log.info(ticketDtoList.toString());
        model.addAttribute("ticket",ticketDtoList);
        return "admin/ticket";
    }

    @GetMapping("board")
    public String board(Model model,
                        @PageableDefault(page = 0, size = 10, sort="id",
                        direction = Sort.Direction.DESC)Pageable pageable){
//        List<BoardDto> boardDtoList = boardService.findAll();
//        log.info("========="+boardDtoList.toString());
//        model.addAttribute("board",boardDtoList);

        List<Board> boardDtoList = boardService.findAllem();
        log.info(boardDtoList.toString());
        model.addAttribute("board",boardDtoList);
        return "admin/board";
    }


}
