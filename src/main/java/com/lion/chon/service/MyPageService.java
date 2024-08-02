package com.lion.chon.service;

import com.lion.chon.dto.MyPageDTO;
import com.lion.chon.entity.UserEntity;
import com.lion.chon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyPageService {

    private final UserRepository userRepository;
    
    // 마이페이지 유저 정보 조회
    public MyPageDTO getUserProfile() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        Optional<UserEntity> exsitingUser = userRepository.findById(username);
        if (exsitingUser.isPresent()) {
            return new MyPageDTO(exsitingUser.get());
        } else{
            return null;
        }
    }

    // 마이페이지 유저 정보 수정
    public MyPageDTO updateProfile(MyPageDTO myPageDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        String username = userDetails.getUsername();
        Optional<UserEntity> exsitingUser = userRepository.findById(username);
        if (exsitingUser.isPresent()) {
            UserEntity userEntity = exsitingUser.get();
            userEntity.setName(myPageDTO.getName());
            userEntity.setEmail(myPageDTO.getEmail());
            userEntity.setPhoneNum(myPageDTO.getPhoneNum());

            userRepository.save(userEntity);

            return new MyPageDTO(exsitingUser.get());
        } else{
            return null;
        }
    }
}
