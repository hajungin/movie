package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cnema")
public class MainController {
    @GetMapping("")
    public String mainView(){
        return "cnema/cnema_main";
    }

}
