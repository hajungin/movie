package com.example.movie.controller;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.User;
import com.example.movie.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@Slf4j
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

    @GetMapping("main")
    public String userMain(){
        return "user/user_main";
    }

    @GetMapping("information")
    public String information(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자의 이름 또는 ID 가져오기
            String username = authentication.getName();
            // 또는 PrincipalDetails로 형변환 후 사용자 정보 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

            // 여기서 userDetails에서 사용자 정보 추출
            User user = userDetails.getUser();

            UserDto userDto = userService.getOneUser(user.getUserNo());
            model.addAttribute("userDto", userDto);
        }
                return "user/user_information";
    }
    @PostMapping("information")
    public String informationEdit(@ModelAttribute("userDto") UserDto userDto){
        userService.update(userDto);
        return "redirect:/cnema";
    }


    @GetMapping("ticket")
    public String ticket(){
        return "user/user_ticket";
    }


}
