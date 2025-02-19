package com.lion.chon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html 템플릿을 반환
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // signup.html 템플릿을 반환
    }
}
