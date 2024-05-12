package com.example.movie.controller;

import com.example.movie.dto.UserDto;
import com.example.movie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("signup")
    public String singup(UserDto userDto){
        return "user/signup";
    }

    @PostMapping("signup")
    public String singup(@Valid UserDto userDto,
                         BindingResult bindingResult){
        try{
            userService.createUser(userDto);
        } catch (DataIntegrityViolationException e){
            // 데이터베이스 관련 예외 처리
            e.printStackTrace(); // 예외 정보 로깅
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다");
            return "user/signup"; // 사용자에게 보여줄 화면 반환
        } catch (Exception e){
            // 다른 예외 처리
            e.printStackTrace(); // 예외 정보 로깅
            bindingResult.reject("signupFailed" ,e.getMessage());
            return "user/signup"; // 사용자에게 보여줄 화면 반환
        }
        return "redirect:/cnema";
    }

    @GetMapping("login")
    public String login(){
        return "user/login";
    }

}
