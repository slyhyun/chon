package com.lion.chon.controller;

import com.lion.chon.dto.UserDTO;
import com.lion.chon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(userDTO);
            return "redirect:/login"; // 회원가입 성공 시 로그인 페이지로 리디렉션
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/signup"; // 회원가입 실패 시 회원가입 페이지로 리디렉션
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute UserDTO userDTO, RedirectAttributes redirectAttributes) {
        try {
            userService.loginUser(userDTO.getId(), userDTO.getPassword());
            return "redirect:/home"; // 로그인 성공 시 홈 페이지로 리디렉션
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/login"; // 로그인 실패 시 로그인 페이지로 리디렉션
        }
    }
}
