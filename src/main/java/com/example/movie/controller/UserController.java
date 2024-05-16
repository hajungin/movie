package com.example.movie.controller;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.dto.UserDto;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.service.BookService;
import com.example.movie.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("signup")
    public String singup(UserDto userDto){
        return "user/signup";
    }

//    @PostMapping("signup")
//    public String signup(@RequestParam("userCheck") String userId, RedirectAttributes redirectAttributes) {
//        boolean check = userService.checkId(userId);
//        if (check) {
//            // 중복된 사용자 ID가 있을 경우
//            redirectAttributes.addFlashAttribute("errorMessage", "이미 사용 중인 사용자 ID입니다.");
//            return "redirect:/user/signup";
//        } else {
//            // 중복된 사용자 ID가 없을 경우
//            redirectAttributes.addFlashAttribute("successMessage", "사용자 ID 중복 확인이 완료되었습니다.");
//            UserDto userDto = new UserDto();
//            userService.createUser(userDto);
//            return "redirect:/cnema";
//        }
//    }



//    @PostMapping("signup")
//    public String singup(@Valid UserDto userDto,
//                         BindingResult bindingResult){
//        try{
//            userService.createUser(userDto);
//        } catch (DataIntegrityViolationException e){
//            // 데이터베이스 관련 예외 처리
//            e.printStackTrace(); // 예외 정보 로깅
//            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다");
//            return "user/signup"; // 사용자에게 보여줄 화면 반환
//        } catch (Exception e){
//            // 다른 예외 처리
//            e.printStackTrace(); // 예외 정보 로깅
//            bindingResult.reject("signupFailed" ,e.getMessage());
//            return "user/signup"; // 사용자에게 보여줄 화면 반환
//        }
//        return "redirect:/cnema";
//    }
    @PostMapping("signup")
    public String singup(@Valid UserDto userDto,
                     BindingResult bindingResult){

        boolean checkUser = userService.checkId(userDto.getUserId());
        if (checkUser) {
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다");
            return "user/signup"; // 사용자에게 보여줄 화면 반환
        }
        if(!userDto.getPassword1().equals(userDto.getPassword2())){
            bindingResult.rejectValue("userPassword2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup";
        }

        userService.createUser(userDto);
        return "redirect:/cnema";
    }


    @GetMapping("check")
    public String check(@RequestParam(name = "userCheck", required = false) String userId,RedirectAttributes redirectAttributes) {
        boolean check = userService.checkId(userId);
        if (check) {
//            // 중복된 사용자 ID가 있을 경우
            redirectAttributes.addFlashAttribute("errorMessage", "이미 사용 중인 사용자 ID입니다.");
        } else {
//            // 중복된 사용자 ID가 없을 경우
            redirectAttributes.addFlashAttribute("successMessage", "사용자 ID 중복 확인이 완료되었습니다.");
        }
        return "user/signup";
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
    public String ticket(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자의 이름 또는 ID 가져오기
            String username = authentication.getName();
            // 또는 PrincipalDetails로 형변환 후 사용자 정보 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

            // 여기서 userDetails에서 사용자 정보 추출
            User user = userDetails.getUser();
            Long userNo = user.getUserNo();

            List<Ticket> ticketList = bookService.viewReservationDetails(userNo);
            model.addAttribute("ticketList", ticketList);
        }
        return "user/user_ticket";
    }
    @PostMapping("/deleted/{ticketNo}")
//    관리자페이지 영화삭제 화면
    public String deleteMovie(@PathVariable("ticketNo") Long ticketNo) {

        bookService.ticketCancel(ticketNo);

        return "redirect:/user/main";
    }

    @GetMapping("delete")
    public String deleteUser(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자의 이름 또는 ID 가져오기
            String username = authentication.getName();
            // 또는 PrincipalDetails로 형변환 후 사용자 정보 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

            // 여기서 userDetails에서 사용자 정보 추출
            User user = userDetails.getUser();
            Long userNo = user.getUserNo();

            UserDto userDto = userService.getOneUser(userNo);
            model.addAttribute("userDto", userDto);
        }
        return "user/user_delete";
    }

    @PostMapping("delete")
    public String delete(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자의 이름 또는 ID 가져오기
            String username = authentication.getName();
            // 또는 PrincipalDetails로 형변환 후 사용자 정보 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

            // 여기서 userDetails에서 사용자 정보 추출
            User user = userDetails.getUser();
            Long userNo = user.getUserNo();

            userService.delete(userNo);
        }

        return "redirect:/logout";
    }

}
