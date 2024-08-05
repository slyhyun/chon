package com.lion.chon.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private int id;
    private String userId;
    private String name;
    private String email;
    private String phoneNum;
    private int age;
    private String gender;
    private String title;
    private String contents;
    private String location;
    private LocalDateTime postDate;
    private int maximumPeople;
    private int applicationPeople;
    private boolean isMine; // 접근한 유저의 게시글인지 여부
}
