package com.lion.chon.dto;

import com.lion.chon.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MyPageDTO {
    private String name;
    private String email;
    private String phoneNum;
    private LocalDate birth;
    private String residence;
    private int age;
    private String gender;

    public MyPageDTO(String name, String email, String phoneNum, LocalDate birth, String residence, int age, String gender) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.birth = birth;
        this.residence = residence;
        this.age = age;
        this.gender = gender;
    }

    public MyPageDTO(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.phoneNum = userEntity.getPhoneNum();
        this.birth = userEntity.getBirth();
        this.residence = userEntity.getResidence();
        this.age = userEntity.getAge();
        this.gender = userEntity.getGender();
    }
}
