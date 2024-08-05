package com.lion.chon.controller;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.service.MyPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyPageController {

    private final MyPageService myPageService;

    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    // 마이페이지 유저 정보 조회
    @GetMapping("/profile")
    public ResponseEntity<MyPageDTO> getUserProfile() {
        MyPageDTO profile = myPageService.getUserProfile();
        return ResponseEntity.ok(profile);
    }

    // 유저 정보 수정
    @PostMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody MyPageDTO myPageDTO) {
        myPageService.updateProfile(myPageDTO);
        return ResponseEntity.ok("Profile updated successfully");
    }

    // 유저 정보 삭제 (회원 탈퇴)
    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawl(@RequestParam String password) {
        boolean isWithdrawn = myPageService.withdrawl(password);
        if (isWithdrawn) {
            return ResponseEntity.ok("User withdrawn successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원 탈퇴 실패");
        }
    }
}
