package com.lion.chon.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
UserDTO {
    private String id;
    private String password;
    private String email;
    private String name;
    private String phoneNum;
    private String residence;
    private LocalDate birth;
    private int age;
    private String gender;
    private LocalDateTime registerDate;
    private String role;
}
