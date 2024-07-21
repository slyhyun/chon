package com.lion.chon.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "title", length =30, nullable = false)
    private String title;

    @Column(name = "contents", length = 500, nullable = false)
    private String contents;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime postDate;

}
