package com.example.movie.controller;

import com.example.movie.dto.LocationDto;
import com.example.movie.dto.SeatDto;
import com.example.movie.service.LocationService;
import com.example.movie.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cnema")
@Slf4j
public class BookController {

    private final LocationService locationService;
    private final SeatService seatService;

    public BookController(LocationService locationService, SeatService seatService) {
        this.locationService = locationService;
        this.seatService = seatService;
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
        log.info("================================================");
        log.info(locationDtoList.stream().toList().toString());

        model.addAttribute("locationDtoList", locationDtoList);
        return "/articles/book/select_theater";
    }

    @GetMapping("/seat")
    public String selectSeatView(Model model){

//        int[] row = new int[6];
//        int[] column = new int[8];
//        for (int i = 0; i < row.length; i++) {
//            row[i] = i;
//        }
//        for (int j = 0; j < column.length; j++) {
//            column[j] = j;
//        }  좌석의 크기가 커지면 for문을 크지 않다면 아래처럼 직접 적어주는게 오히려 유지보수에 좋을 수 있다.

        int[] row = {0,1,2,3,4,5};
        int[] column = {0,1,2,3,4,5,6,7};
        model.addAttribute("row", row);
        model.addAttribute("column", column);
        model.addAttribute("seatDto", new SeatDto());

        return "/articles/book/select_seat";
    }


    @PostMapping("/seat")
    public String selectSeat(@){

        return "redirect:/";
    }
}
