package com.example.movie.controller;

import com.example.movie.dto.SeatDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @GetMapping("/select-seat")
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

        return "/articles/select_seat";
    }

    @PostMapping("/select-seat")
    public String selectSeat(@ModelAttribute("seatDto") SeatDto seatDto){

        return "redirect:/";
    }
}
