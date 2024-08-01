package com.lion.chon.dto;

import com.lion.chon.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

@Getter
public class MyPageDTO {
    private String name;
    private String email;
    private String phoneNum;

    public MyPageDTO(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public MyPageDTO(UserEntity userEntity){
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.phoneNum = userEntity.getPhoneNum();
    }
}
