package com.example.movie.controller;

import com.example.movie.config.PrincipalDetails;
import com.example.movie.constant.SeatCoordinates;
import com.example.movie.dto.*;
import com.example.movie.entity.Ticket;
import com.example.movie.entity.User;
import com.example.movie.service.BookService;
import com.example.movie.service.LocationService;
import com.example.movie.service.MovieService;
import com.example.movie.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cinema")
@Slf4j
public class BookController {

    private final UserService userService;
    private final LocationService locationService;
    private final MovieService movieService;
    private final BookService bookService;

    public BookController(UserService userService, LocationService locationService, MovieService movieService, BookService bookService) {
        this.userService = userService;
        this.locationService = locationService;
        this.movieService = movieService;
        this.bookService = bookService;
    }


    @GetMapping("theater")
    public String selectTheaterView(Model model){
        LocalDate today = LocalDate.now();
        List<LocalDate> dateList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            dateList.add(date);
        }
        model.addAttribute("dateList", dateList);

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
            LocalDate birthDate = LocalDate.parse(userDto.getBirth());

            if (birthDate != null && today != null) {
                int years = Period.between(birthDate, today).getYears();
                int birthMonth = birthDate.getMonthValue();
                int currentMonth = today.getMonthValue();
                int birthDay = birthDate.getDayOfMonth();
                int currentDay = today.getDayOfMonth();

                // 생일이 지났는지 여부 확인
                if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                    years--; // 아직 생일이 오지 않았으면 한 살을 빼줌
                }
                model.addAttribute("years", years);
                log.info("=====================================");
                log.info(String.valueOf(years));

            }
        }

        List<LocationDto> locationDtoList = locationService.findAll();
        model.addAttribute("locationDtoList", locationDtoList);

        List<MoviesDto> moviesDtoList = movieService.findAll();
        model.addAttribute("moviesDtoList", moviesDtoList);
        return "/book/select_theater";
    }

    @PostMapping("/theater")
    public String selectSeat(
            @RequestParam("movie") Long movieNo,
            @RequestParam("location") Long locationNo,
            @RequestParam("date") LocalDate date,
            HttpSession session){
        session.setAttribute("movieNo", movieNo);
        session.setAttribute("locationNo", locationNo);
        session.setAttribute("date", date);

        return "redirect:/cinema/seat";
    }

    @GetMapping("/seat")
    public String selectSeatView(Model model, HttpSession session){
        Long movieNo = (Long) session.getAttribute("movieNo");
        MoviesDto moviesDto = movieService.getOneMovie(movieNo);

        Long locationNo = (Long) session.getAttribute("locationNo");
        LocalDate date = (LocalDate) session.getAttribute("date");

        model.addAttribute("moviesDto", moviesDto);
        model.addAttribute("locationNo", locationNo);
        model.addAttribute("date", date);

//        List<SeatDto> seatDtoList = bookService.findSeatByMovieLocationAndDate(movieNo, locationNo, date); JPQL 사용전
//        log.info("==============2====================");
//        log.info(seatDtoList.toString());

        List<SeatDto> seatDtoList = bookService.searchSeatByMovieLocationAndDate(movieNo, locationNo, date);

        int numRows = 6;
        int numColumns = 8;

        boolean[][] seats = bookService.convertToSeatArray(seatDtoList, numRows, numColumns);

// 모델에 좌석 배열을 추가
        model.addAttribute("seats", seats);
        return "/book/select_seat";
    }

    @PostMapping("/seat")
    public String selectSeat(@RequestParam("movieNo") Long movieNo,
                             @RequestParam("locationNo") Long locationNo,
                             @RequestParam("date") LocalDate date,
                             @RequestParam("selectedSeats") String selectedSeats,
                             @RequestParam("totalPrice") int totalPrice){

        log.info(String.valueOf(movieNo));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자의 이름 또는 ID 가져오기
            String username = authentication.getName();
            // 또는 PrincipalDetails로 형변환 후 사용자 정보 가져오기
            PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();

            // 여기서 userDetails에서 사용자 정보 추출
            User user = userDetails.getUser();
            Long userNo = user.getUserNo();
            bookService.ticketBookService(movieNo, locationNo, userNo,date, selectedSeats, totalPrice);
        }

        return "redirect:/cinema";
    }

}
