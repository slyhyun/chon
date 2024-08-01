package com.lion.chon.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class SignUpDTO {
    private String id;
    private String password;
    private String email;
    private String name;
    private String phoneNum;
    private String residence;
    private LocalDate birth;
    private int age;
    private String gender;
}
