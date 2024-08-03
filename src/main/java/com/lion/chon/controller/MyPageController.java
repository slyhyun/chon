package com.lion.chon.controller;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.UserRepository;
import com.lion.chon.service.MyPageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MyPageController {

    private final UserRepository userRepository;
    private final MyPageService myPageService;

    public MyPageController(UserRepository userRepository, MyPageService myPageService){
        this.userRepository = userRepository;
        this.myPageService = myPageService;
    }

    // 마이페이지 유저 정보 조회
    @GetMapping("/profile")
    public MyPageDTO getUserProfile() {

        return myPageService.getUserProfile();

    }

    // 유저 정보 수정
    @PostMapping("/profile")
    public void updateProfile(@RequestBody MyPageDTO myPageDTO) {
        myPageService.updateProfile(myPageDTO);
    }

    // 유저 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/withdrawl")
    public void withdrawl() {
        myPageService.withdrawl();
    }
}
