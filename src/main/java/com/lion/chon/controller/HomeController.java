package com.lion.chon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home"; // home.html 템플릿을 반환
    }

    @GetMapping("/")
    public String rootHome(){ return "home";}
}
