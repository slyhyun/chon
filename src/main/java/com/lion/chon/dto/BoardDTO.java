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
    private String email;
    private String title;
    private String contents;
    private String location;
    private LocalDateTime postDate;
}
