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
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "contents", length = 500, nullable = false)
    private String contents;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "post_date", nullable = false)
    private LocalDateTime postDate;

    @Column(name = "maximum_people")
    private Integer maximumPeople;

    @Column(name = "application_people")
    private Integer applicationPeople;
}
