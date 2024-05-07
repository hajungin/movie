package com.example.movie.controller;

import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.UserDto;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {
    private final UserService userService;
    private final MovieService movieService;

    public AdminController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("")
    public String adminView(Model model){
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user",userDtoList);
        return "admin/main";
    }

    @GetMapping("user")
    public String userView(Model model){
        List<UserDto> userDtoList = userService.findAll();
        model.addAttribute("user",userDtoList);
        return "admin/user";
    }

    @GetMapping("update")
    public String updateView(@RequestParam("updateId")Long userNo,
                             Model model){
        UserDto userDto = userService.getOneUser(userNo);
        model.addAttribute("userDto",userDto);
        return "admin/user_update";
    }

    @PostMapping("update")
    public String updateView(@Valid @ModelAttribute("userDto") UserDto userDto,
                             BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "admin/user_update";
        }
        if (!userDto.getPassword1().equals(userDto.getPassword2())){
            bindingResult.rejectValue("password2","passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "admin/user_update";
        }
        log.info("==============="+userDto.toString());

        userService.update(userDto);

        log.info("==============="+userDto.toString());
        // 유효성 검사를 통과한 경우, 해당 로직을 처리하고 적절한 뷰로 이동합니다.
        return "redirect:/admin/user";
    }

    @PostMapping("/delete/{deleteId}")
    public String delete(@PathVariable("deleteId")Long id){
        userService.delete(id);
        return "redirect:/admin/user";
    }

    @GetMapping("movie")
    public String movieView(Model model){
        List<MoviesDto> moviesDtoList = movieService.findAll();
        model.addAttribute("movie",moviesDtoList);
        return "admin/movie";
    }

    @GetMapping("movie_update")
    public String movieUpdateView(@RequestParam("updateId")Long movieNo,
                             Model model){

        MoviesDto moviesDto = movieService.getOneMovie(movieNo);
        model.addAttribute("movie",moviesDto);
        return "admin/movie_update";
    }

    @PostMapping("/deleted/{deleteId}")
    public String deleteMovie(@PathVariable("deleteId")Long movieNo){
        movieService.delete(movieNo);
        return "redirect:/admin/movie";
    }

}
