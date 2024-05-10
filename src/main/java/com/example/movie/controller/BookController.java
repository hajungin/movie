package com.example.movie.controller;

import com.example.movie.constant.SeatCoordinates;
import com.example.movie.dto.LocationDto;
import com.example.movie.dto.MoviesDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.dto.TicketDto;
import com.example.movie.service.BookService;
import com.example.movie.service.LocationService;
import com.example.movie.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cnema")
@Slf4j
public class BookController {

    private final LocationService locationService;
    private final MovieService movieService;
    private final BookService bookService;

    public BookController(LocationService locationService, MovieService movieService, BookService bookService) {
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

        return "redirect:/cnema/seat";
    }

    @GetMapping("/seat")
    public String selectSeatView(Model model, HttpSession session){
        Long movieNo = (Long) session.getAttribute("movieNo");
        Long locationNo = (Long) session.getAttribute("locationNo");
        LocalDate date = (LocalDate) session.getAttribute("date");

        model.addAttribute("movieNo", movieNo);
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
                             @RequestParam("selectedSeats") String selectedSeats){

        log.info(String.valueOf(movieNo));

        bookService.ticketBookService(movieNo, locationNo, date, selectedSeats);

        return "redirect:/cnema";
    }

}
