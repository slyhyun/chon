package com.lion.chon.controller;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.service.MyPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    // 마이페이지 유저 정보 조회
    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        MyPageDTO profile = myPageService.getUserProfile();
        model.addAttribute("profile", profile);
        return "profile";
    }

    // 유저 정보 수정
    @PostMapping("/profile")
    public String updateProfile(@RequestBody MyPageDTO myPageDTO) {
        myPageService.updateProfile(myPageDTO);
        return "redirect:/profile";
    }
}
