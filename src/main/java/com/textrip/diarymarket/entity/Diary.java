package com.textrip.diarymarket.entity;


import com.textrip.diarymarket.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId; // ✅ PK로 설정된 ID

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String imagePath;
    private String audioPath;
    private String tags;

    private Integer likeCount;
    private Integer viewCount;
    private Integer shareCount;

    private Boolean isPrivate;
    private LocalDateTime createdAt;
}
