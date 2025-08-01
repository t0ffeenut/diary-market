package com.textrip.diarymarket.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = true)  // 이메일은 null 허용
    private String email;

    @Column(unique = true, nullable = false) // 반드시 존재해야 함
    private Long kakaoId;

    private String nickname;

    private String profileImage;

    private LocalDateTime joinDate;

    public User() {}

    public User(String email, String nickname, String profileImage, LocalDateTime joinDate) {
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.joinDate = joinDate;
    }
}
