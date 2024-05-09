package com.example.movie.controller;

import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.UserDto;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
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

    private String uploadDir;
    private final UserService userService;
    private final MovieService movieService;

    public AdminController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("")
//    관리자 페이지 메인 화면
    public String adminView(Model model){
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user",userDtoList);
        return "admin/main";
    }

    @GetMapping("user")
//    관리자페이지 회원관리 화면
    public String userView(Model model){
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user",userDtoList);
        return "admin/user";
    }

    @GetMapping("update")
//    관리자페이지 회원 수정화면
    public String updateView(@RequestParam("updateId")Long userNo,
                             Model model){
        UserDto userDto = userService.getOneUser(userNo);
        model.addAttribute("userDto",userDto);
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
        userService.delete(id);
        return "redirect:/admin/user";
    }

    @GetMapping("movie")
    public String movieView(Model model) {
        List<MoviesDto> moviesDtoList = movieService.findAll();
        model.addAttribute("movie", moviesDtoList);
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


}
