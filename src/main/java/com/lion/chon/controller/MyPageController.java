package com.lion.chon.controller;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MyPageController {

    private final UserRepository userRepository;

    public MyPageController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public MyPageDTO getUserProfile() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        Optional<UserEntity> exsitingUser = userRepository.findById(username);
        if (exsitingUser.isPresent()) {
            MyPageDTO myPageDTO = new MyPageDTO(exsitingUser.get());
            return myPageDTO;
        } else{
            return null;
        }
    }
}
